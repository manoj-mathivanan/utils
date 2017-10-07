package outlook;

public class test {

	public static void main(String[] args) {
		
		//initialize
		Outlook outlook = new Outlook("I074667", "7MYnameis");
		
		//to get contact details
		Contact contactDetails = outlook.getDetails("manoj.mathivanan@sap.com");
		
	}

}
