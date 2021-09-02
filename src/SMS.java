
public class SMS extends Communication {

	
	//Constructor of SMS Class
	public SMS(String numberone, String numbertwo, int aDay, int aMonth, int aYear,String aMessage) {
		super(numberone, numbertwo, aDay, aMonth, aYear);
		 message=aMessage;
	}
	
	
     //Information about an SMS
	public void printInfo() {
		System.out.println("This SMS has the following info" );
		System.out.println("Between "+ number1 + " --- " + number2 );
		System.out.println("on " + year + "/" + month +"/" + day );
		System.out.println("Text: " + message);
	}
	
}
