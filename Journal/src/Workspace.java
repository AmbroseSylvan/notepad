/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
/* Workspace.java requires no other files. */
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
 
public class Workspace extends JPanel  implements ActionListener {
    
	protected static JTextArea textArea;
	protected static JTextField FlagField;
	JButton submitBttn = new JButton("Commit . . ."); 
    JButton saveBttn = new JButton("Save");
    JButton loadBttn = new JButton("Load");
    JLabel FlagLabel = new JLabel ("Enter flag for this entry below");
	String Schm = "Journal";
    String Text;
    String FlagName;
	String FlagID;
    String Query;
    
    public Workspace() {
        super(new GridBagLayout());
        
        //creating Components
        textArea = new JTextArea(20, 20);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        FlagField = new JTextField(20);
        JScrollPane scrollPane = new JScrollPane(textArea);
    
        //setting up buttons
        submitBttn.setActionCommand("Commit");
        submitBttn.setMnemonic(KeyEvent.VK_C);
        submitBttn.setToolTipText("Click this button to commit to memory.");
        submitBttn.addActionListener(this);
        
        saveBttn.setActionCommand("Save");
        saveBttn.setMnemonic(KeyEvent.VK_S);
        saveBttn.setToolTipText("Click this button to save your work for later.");
        saveBttn.addActionListener(this);
        
        loadBttn.setActionCommand("Load");
        loadBttn.setMnemonic(KeyEvent.VK_L);
        loadBttn.setToolTipText("Click this button to load previous workspace.");
        loadBttn.addActionListener(this);        
        
        //		Add Components to this panel.
        //Add scrollPane
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 5;
        c.gridheight = 20;
        c.gridx = 0;
        c.gridy = 0;
        add(scrollPane, c);
        
        //Add submitBttn
        GridBagConstraints d = new GridBagConstraints();
        d.gridwidth = 1;
        d.gridheight = 1;
        d.gridy = 21;
        d.gridx = 1;
        add(submitBttn, d);
        
        
        //Add saveBttn
        GridBagConstraints e = new GridBagConstraints();
        e.gridwidth = 1;
        e.gridheight = 1;
        e.gridy = 21;
        e.gridx = 0;
        add(saveBttn, e);
        
        //Add loadBttn
        GridBagConstraints f = new GridBagConstraints();
        f.gridwidth = 1;
        f.gridheight = 1;
        f.gridy = 21;
        f.gridx = 2;
        add(loadBttn, f);
        
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
        add(FlagField, h);
        
    }
 
    
    public void actionPerformed(ActionEvent e)
    {
    	Text = textArea.getText();
    	String FlagID;

    	//different actions depending on which button is the source of the actionevent
    	if (e.getSource() == submitBttn)
    	{
    		//get the flag id from user input and reference to database
    		FlagName = FlagField.getText();
    		FlagID = IdentifyFlag(FlagName);
    		System.out.println(FlagID);
    		
    		//test that id was returned
        	System.out.println("this is the flag id " + FlagID);
        	
        	//insert entry with flag ID in the test table
        	Query = "INSERT INTO log (entry, flag) VALUES ('" + Text + "', " + FlagID + ")";
        	SQLcommands.Insert(Schm, Query);
        	
    	} else if (e.getSource() == saveBttn)
    	{
    		Query = "INSERT INTO workspacelog(entry) VALUES ('" + Text + "')";
        	SQLcommands.Insert(Schm, Query);
        	
    	} else if (e.getSource() == loadBttn)
    	{
    		textArea.setText(
    				SQLcommands.Select(Schm, "SELECT entry FROM workspaceLog WHERE "
    						+ "timestamp = (SELECT MAX(timestamp) FROM WorkspaceLog)"));
    	}
    }
    
    public String IdentifyFlag(String FlagName)
	{
		String flagID;
		
		flagID = SQLcommands.Select
						(Schm, "SELECT idflag FROM flag WHERE flag_name = '" + FlagName + "';");
		
		if (flagID == null)
		{
			System.out.println("no match");
			System.out.println(flagID);
			SQLcommands.Insert(Schm, 
					"INSERT INTO flag (flag_name) VALUES ('" + FlagName + "');");
			flagID = SQLcommands.Select
					(Schm, "SELECT idFlag FROM flag WHERE flag_name = '" 
							+ FlagName + "';");
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
	}
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Workspace");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new Workspace());
 
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