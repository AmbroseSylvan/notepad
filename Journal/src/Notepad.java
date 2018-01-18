import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Notepad {

	Scanner scanner = new Scanner(System.in);
	private static final String newline = "\n";
	
	public static void TextArea()
	{
	JTextField textfield = new JTextField(20);
	JTextArea workspace = new JTextArea(5, 20);
	workspace.setEditable(true);
	JScrollPane scrollPane = new JScrollPane(workspace); 
	
	String text = workspace.getText();
	//. . . 
	workspace.append(text + newline);
	}
	
	
	JButton Submit = new JButton(); //this will call Flag() when pressed
	JButton Save = new JButton();
	JButton Load = new JButton();
	
	public int Flag() throws ClassNotFoundException, SQLException
	{
		//get a flag name from user
		String FlagName;
		int flagID = 0;
		
		FlagName = scanner.nextLine();
		flagID = getFlagID(FlagName); //select from FLAG, insert if not found
		
		return flagID;
		
	}
	
	public int getFlagID(String FlagName) throws SQLException, ClassNotFoundException
	{
		int flagID = 0;
		String query = "SELECT * FROM FLAG WHERE Flag_Name = " + FlagName;
				
		Connection conn = Connect.Connect();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		flagID = rs.getInt(1);
		
		if (flagID != 0)
		{
			conn.close();
			st.close();
			rs.close();
		}
		else if (flagID == 0) //If no flag with the specified name is found then add it to the table
		{
			conn.close();
			st.close();
			rs.close();
			flagID = 0;
			
			Insert(FlagName);
			flagID = getFlagID(FlagName);		
		}
		
		return flagID;
	}
	
	public void Insert(String FlagName) throws ClassNotFoundException, SQLException
	{
		
		/String query = "INSERT " + FlagName + " INTO Flag_Name";
		Connection conn = Connect.Connect();
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		preparedStmt.execute();
		conn.close();
	}
	
	public static void main(String[] args) {

			TextArea();
	}

}
