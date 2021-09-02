import java.awt.Color;
//import java.awt.Container;
import java.awt.Dimension;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;


public class SuspectInfoFrame extends JFrame {
    Border grey;
	private JPanel MegaPanel, SusInfopanel,SMSpanel,Partnerspanel,SuggestedPartpanel,Countrypanel;
	private JButton findSMSButton;
	private JButton ReturntoMainB;
	private JTextField SusName,SusCodeName,PhoneField,aNumberField;
	private JTextArea PartnersField,SuggPartField,CountryField,SMSField;
	private JLabel SuspLabel;
	private JLabel SuggPartnersLabel;
	Registry registryC;
	int position;
	public SuspectInfoFrame(Registry r, String name,int pos) {

		registryC=r;
		position=pos;
		MegaPanel=new JPanel();
		
		//Suspect Basic Info Panel
		SusInfopanel= new JPanel();
		SusName= new JTextField(name);
		SusCodeName= new JTextField(registryC.Suspects.get(pos).getCodeName());
		PhoneField= new JTextField(( registryC.Suspects.get(pos)).phonelist.toString());
		PhoneField.setPreferredSize(new Dimension(200,50));
		SusInfopanel.add(SusName);
		SusInfopanel.add(SusCodeName);
		SusInfopanel.add(PhoneField);
		grey=BorderFactory.createLineBorder(Color.gray,1);
		SusInfopanel.setBorder(grey);
		
		
	
		//Suspicious messages between a number given and the Suspect
		SMSpanel= new JPanel();
		aNumberField=new JTextField(10);
		SMSField=new JTextArea();
		SMSField.setPreferredSize(new Dimension(200,150));
		findSMSButton =new JButton("Find SMS");
		SMSpanel.setBorder(grey);
		SMSpanel.add(aNumberField);
		SMSpanel.add(SMSField);
		SMSpanel.add(findSMSButton);
		FindSmsButtonListener findlistener= new FindSmsButtonListener();
		findSMSButton.addActionListener(findlistener);
		
		
		
		//Possible Partners of the Suspect
		Partnerspanel= new JPanel();
		SuspLabel= new JLabel("Partners");
		PartnersField= new JTextArea();
		String ConnSusps="";
		for(int i=0;i<registryC.Suspects.get(pos).ConnSuspects.size();i++) {
			ConnSusps+=registryC.Suspects.get(pos).ConnSuspects.get(i).getName() +"," + registryC.Suspects.get(pos).ConnSuspects.get(i).getCodeName() + "\n";
			
		}
		PartnersField.setText(ConnSusps);
		PartnersField.setPreferredSize(new Dimension(200,150));
		Partnerspanel.add(SuspLabel);
		Partnerspanel.add(PartnersField);
		Partnerspanel.setBorder(grey);
		
		
		//Suggested Possible Partners of the Suspect
		SuggestedPartpanel= new JPanel();
		SuggPartnersLabel= new JLabel("Suggested Partners ----->");
		SuggPartField=new JTextArea();
		String SuggParts="";
		for(int i=0;i<registryC.Suspects.get(pos).getSuggestedPossiblePartners().size();i++) {
			SuggParts+=registryC.Suspects.get(pos).getSuggestedPossiblePartners().get(i).getName()+"\n";
		}
		SuggPartField.setText(SuggParts);
		SuggPartField.setPreferredSize(new Dimension(200,150));
		SuggestedPartpanel.add(SuggPartnersLabel);
		SuggestedPartpanel.add(SuggPartField);
		SuggestedPartpanel.setBorder(grey);
		
		//Same Country Suspects
		Countrypanel= new JPanel();
		CountryField=new JTextArea();
		String Suscountry=registryC.Suspects.get(pos).getCountry();
		String CountryPartners="Suspects coming from " +Suscountry+"\n" ;
		for(int i=0;i<registryC.SuspectsFromCountry(Suscountry).size();i++) {
			CountryPartners+= registryC.SuspectsFromCountry(Suscountry).get(i).getName()+"\n";
		}
		CountryField.setText(CountryPartners);
		CountryField.setPreferredSize(new Dimension(250,70));
		Countrypanel.add(CountryField);
		Countrypanel.setBorder(grey);
		//The full window panel
		MegaPanel.add(SusInfopanel);
		MegaPanel.add(SMSpanel);
		MegaPanel.add(Partnerspanel);
		MegaPanel.add(SuggestedPartpanel);
		MegaPanel.add(Countrypanel);
		ReturntoMainB= new JButton("Return to Main Screen");
		MegaPanel.add(ReturntoMainB);
		
		ReturnButtonListener listener = new ReturnButtonListener();
		ReturntoMainB.addActionListener(listener);
		
		
		
		this.setContentPane(MegaPanel);
		this.setTitle("Suspect Page");
		this.setSize(432,730);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	//Return to Main Screen Action
	class ReturnButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				new SearchFrame(registryC);
				SuspectInfoFrame.this.dispose();
			
			}
		}
	
	//Find SMS Action 
	class FindSmsButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String numberGiven=aNumberField.getText();
			ArrayList<SMS>messages=registryC.getMessagesBetween(registryC.Suspects.get(position), numberGiven);
			int sizeOfMessArray=registryC.getMessagesBetween(registryC.Suspects.get(position), numberGiven).size();
			String allMess="";
			for (int i=0; i<sizeOfMessArray;i++) {
				allMess+=messages.get(i).message;
			}
			SMSField.setText(allMess);
			
		}
	}
}
