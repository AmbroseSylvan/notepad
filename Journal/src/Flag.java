import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Flag extends JPanel implements ActionListener{
	
	//get a flag name from user
	
	static String FlagName;
	JTextField TextField = new JTextField(20);
	JButton Send = new JButton("Send");
	JButton Cancel = new JButton("Cancel");
	public String FlagID;
	static JFrame frame = new JFrame("Flags");
	
	public Flag()
	{
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 5;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(TextField, c);
		
        GridBagConstraints d = new GridBagConstraints();
        d.gridwidth = 1;
        d.gridheight = 1;
        d.gridx = 0;
        d.gridy = 1;
        add(Send, d);
        
        GridBagConstraints e = new GridBagConstraints();
        e.gridwidth = 1;
        e.gridheight = 1;
        e.gridx = 3;
        e.gridy = 1;
        add(Cancel, e);
        
        Send.setActionCommand("Send");
        Send.addActionListener(this);
	}
	
	public static void FlagWindow()
	{
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(new Flag());
        
        frame.pack();
        frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		FlagName = TextField.getText();
		TextField.setText(null);
		FlagID = IdentifyFlag(FlagName);
		System.out.println(FlagID);
	}
	
	public static String IdentifyFlag(String FlagName)
	{
		String flagID;
		
		flagID = SQLcommands.Select
						("SELECT idflag FROM flag WHERE flag_name = '" + FlagName + "';");
		
		if (flagID == null)
		{
			System.out.println("no match");
			System.out.println(flagID);
			SQLcommands.Insert("INSERT INTO flag (flag_name) VALUES ('" + FlagName + "');");
			flagID = SQLcommands.Select
					("SELECT idFlag FROM flag WHERE flag_name = '" + FlagName + "';");
			if (flagID == null)
			{
				System.out.println("failed insert");
				System.out.println(flagID);
			} else 
			{
				return flagID;
			}
			return flagID;
		} else 
		{
			System.out.println("match");
			System.out.println(flagID);
			return flagID;
		}
		
		
		//if null then insert(flagname)
		//	select(flagname)
		//	return flagid;
		//the returned flagid is added to the insert() called by SubmitBttn
		
		
	}

}
