package outlook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.apache.directory.api.ldap.model.entry.Entry;

public class Outlook {
private String inumber;
private String password;
	
public Outlook(String inumber,String password)
{
	this.inumber = inumber;
	this.password = password;
}

public Contact getDetails(String email)
{
	Contact cnt = new Contact(email);
	LdapConnection connection = new LdapNetworkConnection("10.66.192.108", 389 );
	try{
		
		connection.bind( "CN="+this.inumber+",OU=I,OU=Identities,DC=global,DC=corp,DC=sap",this.password );
		String st[] = new String[1];
		st[0] = "*";
		EntryCursor cursor = connection.search( "OU=Identities,DC=global,DC=corp,DC=sap", "(&(objectClass=person)(mail=" + email +"))", SearchScope.SUBTREE, st );
		if(cursor.next())
		{
		    Entry entry = cursor.get(); 
		    cnt.setInumber(entry.get("name").toString().substring(6).trim());
		    cnt.setName(entry.get("displayName").toString().substring(13).trim());
		    cnt.setPhone(entry.get("mobile").toString().substring(8).trim());
		    cnt.setDepartment(entry.get("description").toString().substring(13).trim());
		    cnt.setLocation(entry.get("physicalDeliveryOfficeName").toString().substring(28).trim());
		    connection.close(); 
		}
		else
		{
			cnt.setName("Invalid Email");
		}
	}catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return cnt;
}

public boolean sendMail(String from, String to, String subject, String content)
{
    String host = "mail.sap.corp";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);
    try{
       MimeMessage message = new MimeMessage(session);
       message.setFrom(new InternetAddress(from));
       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
       message.setSubject(subject);
       message.setText(content);
       Transport.send(message);
       System.out.println("Sent message successfully....");
    }catch (MessagingException mex) {
       mex.printStackTrace();
       return false;
    }
	return true;
}

public boolean sendMeeting(String from, String to, String subject, String content, String location, Calendar cal,int hoursDuration)
{
	String host = "mail.sap.corp";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);
    MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
    mimetypes.addMimeTypes("text/calendar ics ICS");
    MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
    mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");
    try{
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.setSubject(subject);
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

    Multipart multipart = new MimeMultipart("alternative");
    BodyPart messageBodyPart = buildHtmlTextPart(content);
    multipart.addBodyPart(messageBodyPart);
    cal.add(Calendar.MONTH, -1);
    BodyPart calendarPart = buildCalendarPart(from,to,subject,content,location,cal,hoursDuration);
    multipart.addBodyPart(calendarPart);
    message.setContent(multipart);
 
    Transport transport = session.getTransport("smtp");
    transport.connect();
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
    
    }catch (Exception mex) {
        mex.printStackTrace();
        return false;
     }
	return true;
}

private BodyPart buildHtmlTextPart(String content) throws MessagingException {
	 
    MimeBodyPart descriptionPart = new MimeBodyPart();
    descriptionPart.setContent(content, "text/html; charset=utf-8");

    return descriptionPart;
}

private static SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");

private BodyPart buildCalendarPart(String from,String to,String subject, String content, String location,Calendar starttime, int duration) throws Exception {

    BodyPart calendarPart = new MimeBodyPart();
    Calendar cal = starttime;
    Date start = cal.getTime();
    cal.add(Calendar.HOUR_OF_DAY, duration);
    Date end = cal.getTime();

    String calendarContent =
            "BEGIN:VCALENDAR\n" +
                    "METHOD:REQUEST\n" +
                    "PRODID: BCP - Meeting\n" +
                    "VERSION:2.0\n" +
                    "BEGIN:VTIMEZONE\n" +
                    "TZID:India Standard Time\n" +
                    "BEGIN:STANDARD\n" +
                    "DTSTART:16010101T000000\n" +
                    "TZOFFSETFROM:+0530\n" +
                    "TZOFFSETTO:+0530\n" +
                    "END:STANDARD\n" +
                    "END:VTIMEZONE\n" +
                    "BEGIN:VEVENT\n" +
                    "DTSTAMP:" + iCalendarDateFormat.format(start) + "\n" +
                    "DTSTART:" + iCalendarDateFormat.format(start)+ "\n" +
                    "DTEND:"  + iCalendarDateFormat.format(end)+ "\n" +
                    "SUMMARY:test request\n" +
                    "UID:"+UUID.randomUUID()+"\n" +
                    "ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:"+to+"\n" +
                    "ORGANIZER:MAILTO:"+from+"\n" +
                    "LOCATION:"+location+"\n" +
                    "DESCRIPTION:"+subject+"\n" +
                    "SEQUENCE:0\n" +
                    "PRIORITY:5\n" +
                    "CLASS:PUBLIC\n" +
                    "STATUS:CONFIRMED\n" +
                    "TRANSP:OPAQUE\n" +
                    "BEGIN:VALARM\n" +
                    "ACTION:DISPLAY\n" +
                    "DESCRIPTION:REMINDER\n" +
                    "TRIGGER;RELATED=START:-PT00H15M00S\n" +
                    "END:VALARM\n" +
                    "END:VEVENT\n" +
                    "END:VCALENDAR";

    
    
    calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
    calendarPart.setContent(calendarContent, "text/calendar;method=CANCEL");

    return calendarPart;
}


}
