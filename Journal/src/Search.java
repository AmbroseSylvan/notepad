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

public class Search extends JPanel implements ActionListener {
	
	protected JTextArea textArea;
	protected JTextField idField;
	protected JTextField flagField;
	protected JTextField flag2Field;
	protected JTextField dateField;
	protected JTextField timeField;
	protected JTextField bookField;
	protected JLabel Entry = new JLabel("Text");
	protected JLabel ID = new JLabel("ID");
	protected JLabel Book = new JLabel("Book");
	protected JLabel Flag = new JLabel("Flag");
	protected JLabel Flag2 = new JLabel("Flag 2");
	protected JLabel Date = new JLabel("Date");
	protected JLabel Time = new JLabel("Time");
	
	protected JButton Search = new JButton("Search");
	public static String Query= "SELECT * FROM log WHERE ";

	public Search() {
        super(new GridBagLayout());
        
        //creating Components
        textArea = new JTextArea(10, 20);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        idField = new JTextField(10);
        flagField = new JTextField(10);
        flag2Field = new JTextField(10);
        dateField = new JTextField(10);
        timeField = new JTextField(10);
        bookField = new JTextField(10);
        JScrollPane scrollPane = new JScrollPane(textArea);
    
		Search.addActionListener(this);
		
        //		Add Components to this panel.
        //Add scrollPane and label
		GridBagConstraints x = new GridBagConstraints();
		x.gridwidth = 5;
		x.gridheight = 1;
		x.gridx = 0;
		x.gridy = 0;
		add(Entry, x);
		
        GridBagConstraints a = new GridBagConstraints();
        a.gridwidth = 5;
        a.gridheight = 9;
        a.gridx = 0;
        a.gridy = 1;
        add(scrollPane, a);
        
        //add label and field for ID
        GridBagConstraints b = new GridBagConstraints();
        b.gridwidth = 5;
        b.gridheight = 1;
        b.gridx = 5;
        b.gridy = 0;
        add(ID, b);
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 5;
        c.gridheight = 1;
        c.gridx = 5;
        c.gridy = 1;
        add(idField, c);
        
        //add label and field for Book
        GridBagConstraints d = new GridBagConstraints();
        d.gridwidth = 5;
        d.gridheight = 1;
        d.gridx = 10;
        d.gridy = 0;
        add(Book, d);
        
        GridBagConstraints e = new GridBagConstraints();
        e.gridwidth = 5;
        e.gridheight = 1;
        e.gridx = 10;
        e.gridy = 1;
        add(bookField, e);
        
        //add label and field for Flag
        GridBagConstraints f = new GridBagConstraints();
        f.gridwidth = 5;
        f.gridheight = 1;
        f.gridx = 5;
        f.gridy = 3;
        add(Flag, f);
        
        GridBagConstraints g = new GridBagConstraints();
        g.gridwidth = 5;
        g.gridheight = 1;
        g.gridx = 5;
        g.gridy = 4;
        add(flagField, g);
        
        //add label and field for flag2
        GridBagConstraints h = new GridBagConstraints();
        h.gridwidth = 5;
        h.gridheight = 1;
        h.gridx = 10;
        h.gridy = 3;
        add(Flag2, h);
        
        GridBagConstraints i = new GridBagConstraints();
        i.gridwidth = 5;
        i.gridheight = 1;
        i.gridx = 10;
        i.gridy = 4;
        add(flag2Field, i);
        
        //add label and field for date
        GridBagConstraints j = new GridBagConstraints();
        j.gridwidth = 5;
        j.gridheight = 1;
        j.gridx = 5;
        j.gridy = 5;
        add(Date, j);
        
        GridBagConstraints k = new GridBagConstraints();
        k.gridwidth = 5;
        k.gridheight = 1;
        k.gridx = 5;
        k.gridy = 6;
        add(dateField, k);
        
        //add label and field for time
        GridBagConstraints l = new GridBagConstraints();
        l.gridwidth = 5;
        l.gridheight = 1;
        l.gridx = 10;
        l.gridy = 5;
        add(Time, l);
        
        GridBagConstraints m = new GridBagConstraints();
        m.gridwidth = 5;
        m.gridheight = 1;
        m.gridx = 10;
        m.gridy = 6;
        add(timeField, m);
        
        //add search button
        GridBagConstraints n = new GridBagConstraints();
        n.gridwidth = 10;
        n.gridheight = 1;
        n.gridx = 5;
        n.gridy = 9;
        add(Search, n);
	}

	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new Search());
 
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

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		SQLcommands.Select("Journal", SearchTerm(Query));
	}	
	
	public String SearchTerm(String query2)
	{
		String str = idField.getText();
		String term[] = str.split(", ");
		String condition[] = new String[term.length];
		
		//use FOR loop to convert terms into WHERE conditions
		//this loop checks each term[] for the - char. 
		//if a - char is found then the containing term is split at that index. 
		//the - char identifies a range[] of integers used in the search condition.
		//every condition[] will be used in the WHERE clause of a db query. 
		
		//create the condition[] array.
		for (int i = 0; i < term.length; i++){
		if (term[i] != null){
			if (term[i].contains("-"))
			{
				String range[] = term[i].split("-");
				condition[i] = "(idlog >= " + range[0] + " OR idlog =< " + range[1] +") ";
			}else{
				condition[i] = "idlog = " + term[i] + " ";
			}
		}
		}
		
		//append condition[] to Query
		for (int j = 0; j <= condition.length; j++){		
		if (condition[j] != null){
			if ( j < condition.length){
				Query = Query + condition[j] + "OR ";
				}else if (j == condition.length){
					Query = Query + condition[j];
			}
		}
		}
		
		return Query;
	}
}


