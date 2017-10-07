package outlook;

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


}
