import java.util.ArrayList;
public class Registry {
	private String num1;
	private String num2;
	
	ArrayList<Suspect> Suspects= new ArrayList<Suspect>();//Creation of Suspects List
	ArrayList<Communication> Communications = new ArrayList<Communication>();//Creation of a list that contains the communications
	                                                                         //between the suspects
	
	//Adding someone in the Suspects' List
	public void addSuspect(Suspect aSuspect) {
		
		Suspects.add(aSuspect);
			}
	
	
	//Adding a Communication between 2 suspects
	public void addCommunication(Communication aCommunication) {
		
		Communications.add(aCommunication);
		
		num1=aCommunication.number1;
		num2=aCommunication.number2;
		
		for (int i=0 ; i<Suspects.size();i++) {
			if (Suspects.get(i).phonelist.contains(num1) ) {
				Suspect caller = Suspects.get(i);
				
				for (int j=0 ; j<Suspects.size(); j++) {
					
					if(Suspects.get(j).phonelist.contains(num2)) {
					Suspect receiver = 	Suspects.get(j);
					caller.addtoList(receiver);
					receiver.addtoList(caller);
						
					}
				}
			}
		}
	}

	
	
	// Returns the Suspect with the most partners
	public Suspect getSuspectWithMostPartners() {
		
		Suspect mostPartnered=Suspects.get(0);
		for (int i=1;i<Suspects.size();i++) {
			if (Suspects.get(i).ConnSuspects.size()>mostPartnered.ConnSuspects.size()){
				mostPartnered=Suspects.get(i);
			}
			
		}
		
		return mostPartnered;
	}
	
	//Returns the longest phone call between 2 suspects
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2) {
		
		Communication Longest= Communications.get(0);
		for (int i=1;i<7;i++) {
			
			if (number1==Communications.get(i).number1){
				
				if (number2==Communications.get(i).number2){
					if (Communications.get(i).callDur > Longest.callDur) {
						
						Longest = Communications.get(i);
					}
				}
			}
		}
		return (PhoneCall)Longest;
	}
	
	
	
	//Returns suspicious messages between two suspects
	public ArrayList<SMS> getMessagesBetween(Suspect givenSus, String number){
		
		ArrayList<SMS> susmessages= new ArrayList<SMS>();
			for(int i=0;i<givenSus.phonelist.size();i++ ) {
			for (int j=7; j<14;j++) {
				if(givenSus.phonelist.get(i)==Communications.get(j).number1 || givenSus.phonelist.get(i)==Communications.get(j).number2 ) {
					if(Communications.get(j).number1.equals(number)||Communications.get(j).number2.equals(number)){
						if (Communications.get(j).message.contains("Bomb") || Communications.get(j).message.contains("Attack")
								|| Communications.get(j).message.contains("Explosives") || Communications.get(j).message.contains("Gun")) {
										
										susmessages.add((SMS)Communications.get(j));
						}
						
						
					}
				}
			}
		}
		return susmessages;
	}
	
	
	
	//Prints the names of suspects of a certain country provided
	ArrayList <Suspect> SuspectsFromCountry(String country) {
		ArrayList SameCountrySusps= new ArrayList<Suspect>();
		for(int i=0; i<Suspects.size();i++) {
			if(Suspects.get(i).getCountry()==country && !SameCountrySusps.contains(Suspects.get(i))) {
				SameCountrySusps.add(Suspects.get(i));
			}
		}
		return SameCountrySusps;
	}
}
