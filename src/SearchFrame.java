import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
public class SearchFrame extends JFrame {

	private JPanel panel;
	private JButton findButton;
	private JTextField SusName;
	ArrayList<Suspect> someSuspects; 
	Registry newr;
	public SearchFrame (Registry r) {
		newr=r;
		someSuspects=r.Suspects;
		panel= new JPanel();
		findButton= new JButton("Find");//Button to Find A Suspect
		panel.add(findButton);
		SusName= new JTextField("Please enter a suspect's name");//Entering a Suspect to Find
		panel.add(SusName);
		
		ButtonListener listener = new ButtonListener();
		findButton.addActionListener(listener);
		
		this.setContentPane(panel);
		this.setSize(350, 200);
		this.setTitle("Find Suspect");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		
	}
	
//Action to Find a Suspect
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String aName= SusName.getText();
			boolean exists=false;
			for(int i=0;i<someSuspects.size();i++) {	
				if(aName.equals(someSuspects.get(i).getName())) {
					SuspectInfoFrame susframe=new SuspectInfoFrame(newr,aName,i);
					SearchFrame.this.dispose();
					exists=true;
				}
	         }
			if(exists==false) {
				JOptionPane.showMessageDialog(null,"Suspect " +aName + " does not exist."); 
				}
		}
	}
}
