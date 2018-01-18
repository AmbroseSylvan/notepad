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
 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class NoteButton extends JPanel
                        implements ActionListener {
    protected JButton b1;
    private String Name;
    private String Tip;

    public NoteButton(String BttnName, String TipText) {
        
    	this.Name = BttnName;
    	this.Tip = TipText;
        
    	b1 = new JButton(this.Name);
        b1.setActionCommand(this.Name);
        
        //set key bindings
        if (BttnName == "to Memory")
        {
        	b1.setMnemonic(KeyEvent.VK_M);
        }else if (BttnName == "Save") 
        {
        	b1.setMnemonic(KeyEvent.VK_S);
        }else if (BttnName == "Load")
        {
        	b1.setMnemonic(KeyEvent.VK_L);
        }
 
        //Listen for actions on buttons.
        b1.addActionListener(this);
   
        //Add Components to this container, using the default FlowLayout.
        add(b1);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (this.Name.equals(e.getActionCommand())) {
            b1.setEnabled(false);
        } else {
            b1.setEnabled(true);
        }
        
        if ("to Memory".equals(e.getActionCommand())) {
        	this.dbcommand = 1;
        	Workspace.Dbcommand(dbcommand);
        } else if ("Save".equals(e.getActionCommand())) {
        	this.dbcommand = 2;
        	Workspace.Dbcommand(dbcommand);
        } else if ("Load".equals(e.getActionCommand())) {
        	this.dbcommand = 3;
        	Workspace.Dbcommand(dbcommand);
        }
    }
}