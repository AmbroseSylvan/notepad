import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Reader extends JPanel implements ActionListener {

	static JTextArea textArea;
	private static String Schm = "Journal";
	JTextField flagField;
	JTextField flagField2;
    JLabel FlagLabel = new JLabel ("Flags");
    JButton loadButton = new JButton ("Load");
	JButton saveButton = new JButton ("Save");

    
	public Reader() {
        super(new GridBagLayout());
        
        //creating Components
        textArea = new JTextArea(20, 20);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        flagField = new JTextField(20);
        flagField2 = new JTextField(20);
        JScrollPane scrollPane = new JScrollPane(textArea);
    
		loadButton.addActionListener(this);
		saveButton.addActionListener(this);
		
        //		Add Components to this panel.
        //Add scrollPane
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 5;
        c.gridheight = 20;
        c.gridx = 0;
        c.gridy = 0;
        add(scrollPane, c);
        
        //Add loadButton
        GridBagConstraints d = new GridBagConstraints();
        d.gridwidth = 1;
        d.gridheight = 1;
        d.gridy = 21;
        d.gridx = 2;
        add(loadButton, d);
        
        //Add saveButton
        GridBagConstraints e = new GridBagConstraints();
        e.gridwidth = 1;
        e.gridheight = 1;
        e.gridy = 21;
        e.gridx = 4;
        add(saveButton, e);
        
        //Add label
        GridBagConstraints g = new GridBagConstraints();
        g.gridwidth = 2;
        g.gridheight = 1;
        g.gridy = 22;
        g.gridx = 0;
        add(FlagLabel, g);
        
        //Add FlagField
        GridBagConstraints h = new GridBagConstraints();
        h.gridwidth = 5;
        h.gridheight = 1;
        h.gridy = 23;
        h.gridx = 0;
        add(flagField, h);
        
        //Add FlagField2
        GridBagConstraints i = new GridBagConstraints();
        i.gridwidth = 5;
        i.gridheight = 1;
        i.gridy = 24;
        i.gridx = 0;
        add(flagField2, i);
        
    }
	
	public void Load()
	{
		String entryQuery = "SELECT entry FROM log WHERE idlog = 1";
		String flagQuery = "SELECT flag FROM log WHERE idlog = 1";
		String flagQuery2 = "SELECT flag2 FROM log WHERE idlog = 1";
		String Text;
		String FlagName;
		String FlagName2;
		
		//retrieve the first row in log, print to console
		Text = SQLcommands.Select(Schm, entryQuery);
		System.out.println(Text);
		System.out.println("is the first entry.");
		//push retrieved text to textArea
		textArea.setText(Text);	
		
		//retrieve the flag of that same row, print to console
		FlagName = SQLcommands.Select(Schm, flagQuery);
		System.out.println(FlagName);
		System.out.println("is this entry's flag.");
		//push retrieved text to flagField
		flagField.setText(FlagName);
		
		//retrieve the 2ndflag of that same row, print to console
		FlagName2 = SQLcommands.Select(Schm, flagQuery2);
		System.out.println(FlagName2);
		System.out.println("is this entry's second flag.");
		//push retrieved text to flagField
		flagField2.setText(FlagName2);
	}
	
	public void Overwrite()
	{
		//this version of Save() OVERWRITES. [renamed] 
		String Text = textArea.getText();
		String Flag = flagField.getText();
		String Flag2 = flagField2.getText();
		String Query = "UPDATE log SET entry = '" + Text 
				+ "', flag = '" + Flag + "', flag2 = '" + Flag2 + "' WHERE idlog = 1";
			
		//update the first row.
		SQLcommands.Insert(Schm, Query);
		textArea.setText(null);
		flagField.setText(null);
		flagField2.setText(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadButton)
		{
			Load();
		} else if (e.getSource() == saveButton)
		{
			Overwrite();
		}
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Reader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new Reader());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
