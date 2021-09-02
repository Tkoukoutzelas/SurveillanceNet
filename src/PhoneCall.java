
public class PhoneCall extends Communication {

	//Constructor of PhoneCall Class
	public PhoneCall(String numberone, String numbertwo, int aDay, int aMonth, int aYear,int duration) {
		super(numberone, numbertwo, aDay, aMonth, aYear);
		callDur=duration;
		
	}

	
	
	//Information about a phone call
	public void printInfo() {
		System.out.println("This phone call has the following info" );
		System.out.println("Between "+ number1 + " --- " + number2 );
		System.out.println("on " + year + "/" + month +"/" + day );
		System.out.println("Duration:" + callDur);
	}
}
