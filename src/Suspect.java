import java.util.ArrayList;

public class Suspect {
	
	
	private String name;
	private String codename;
	private String country;
	private String ActivityCity;
	
	ArrayList<String> phonelist= new ArrayList<String>(); //Creation of a Suspect's Telephone number list
	ArrayList<Suspect> ConnSuspects = new ArrayList<Suspect>();//Creation of a Suspect's possible partner list
	
	
	//Constructor of Suspect Class
	public Suspect(String aName, String aCodename, String aCountry, String anActivityCity)
	{
		name=aName;
		codename=aCodename;
		country=aCountry;
		ActivityCity=anActivityCity;
		
	}
	
	//Adding phone numbers in a Suspect's Telephone Numbers List
	public void addNumber(String aNumber)
	{
		phonelist.add(aNumber);
		
		
	}
	//Checking if two Suspects are connected
	public boolean isConnectedTo(Suspect aSuspect) {
		
		 
			return ConnSuspects.contains(aSuspect);
	
	}
	
	
	//Adding a Suspect in another Suspect's possible partner list
	public void addtoList(Suspect aSuspect) {
		
		boolean cont= this.isConnectedTo(aSuspect);
		if (cont==false) {
			ConnSuspects.add(aSuspect);
			
		}
	}
	
	
	
	// Getting a List of two suspects' possible partners
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {
		
		ArrayList<Suspect> commSusps=this.ConnSuspects;
		commSusps.retainAll(aSuspect.ConnSuspects);
		for (int i=0; i<commSusps.size();i++) {
			if (this.country==commSusps.get(i).country) {
				commSusps.get(i).codename+= '*';
				
			}
		}
		return commSusps;
		
		
		
	}
	
	//Returns the name of the Suspect
	public String getName() {
		
		return this.name;
		
	}
	
	
	//Returns the codename of the Suspect
	public String getCodeName() {
		
		return this.codename;
		
		
	}
	
	
	
	//Returns the country of the Suspect
	public String getCountry() {
		
		return country;
	}
	public ArrayList<String> GetPhoneList(){
		
		return phonelist;
	}


	public ArrayList<Suspect> getSuggestedPossiblePartners(){
		ArrayList<Suspect> SuggestedPPartners= new ArrayList<Suspect>();
		for (int i=0;i<ConnSuspects.size();i++) {
			Suspect anotherP=ConnSuspects.get(i);
			for(int j=0;j<anotherP.ConnSuspects.size();j++) {
				if(!ConnSuspects.contains(anotherP.ConnSuspects.get(j)) && !SuggestedPPartners.contains(anotherP.ConnSuspects.get(j)) 
						&& name!=anotherP.ConnSuspects.get(j).getName()) {
					
					SuggestedPPartners.add(anotherP.ConnSuspects.get(j));
				}
				
			}
		}
		
		return SuggestedPPartners;
	}

}
