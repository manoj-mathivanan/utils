package outlook;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class test {

	public static void main(String[] args) {
		
		//initialize
		Outlook outlook = new Outlook("I074667", "password");
		
		//to get contact details
		Contact contactDetails = outlook.getDetails("manoj.mathivanan@sap.com");
		
		
		
		//to send mail
		//outlook.sendMail("aparajita@sap.com", "manoj.mathivanan@sap.com", "Message from GOD", "You have one wish. Reply to yourself for the wish to be granted.");
		
		//to send meeting
		Calendar startTime = new GregorianCalendar(2014,12,2,13,30,00);
		int durationHours = 3;
		//outlook.sendMeeting("from@sap.com", "to@sap.com", "Subject", "Content", "Location",startTime,durationHours);
	}

}
