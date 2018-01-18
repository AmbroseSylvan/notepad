import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {
	
	private static Connection Connect() throws ClassNotFoundException, SQLException
	{
		//create a mySQL database connection
		String myUrl = "jdbc:mysql://localhost/Game_Collection";
		Connection conn = DriverManager.getConnection(myUrl, "root", "SequelCity2000");
		return conn;
	}
	
	//Is resultset empty?
	
	public static void EmptyResultSet(ResultSet res)
	{
		ResultSet rs = res;
		try {
			if (rs.next() == false)
			{
				System.out.println("resultset is empty");
			}else{
					System.out.println("resultset populated");
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void SelectFirstLine()
	{
		try
		{
			Connection conn = Connect();
			String query = "SELECT Master_id,Title,Platform,Publisher,Developer,Region FROM Games_Master";
			//create the java statement
			Statement st = conn.createStatement();
			//execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			
			//test for empty result set
			
			EmptyResultSet(rs);
			
			//iterate thru the java resultset
			while (rs.next())
			{
				int Master_id = rs.getInt("Master_id");
	            //System.out.println(Master_id);
				String Title = rs.getString("Title");
	            //System.out.println(Title);
				String Platform = rs.getString("Platform");
	            //System.out.println(Platform);
				String Publisher = rs.getString("Publisher");
				//System.out.println(Publisher);
				String Developer = rs.getString("Developer");
				//System.out.println(Developer);
				String Region = rs.getString("Region");
				//System.out.println(Region);
				
				//print the results
				System.out.format("%s, %s, %s, %s, %s, %s\n", Master_id, Title, Platform, Publisher, Developer, Region);
			}
			st.close();
			conn.close();
			rs.close();
			
		}
		catch (SQLException | ClassNotFoundException e)
		{
			System.err.println("Got an exception");
			System.err.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{

		//test that JDBC connector J works
		Connection conn = Connect();
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    // Success.
		    System.out.println("success to connect");
		} catch (ClassNotFoundException e) {
		    // Fail.
			System.out.println("fail to connect");
		}
		conn.close();
		//this tests that connection thru jdbc works.
		
		
		//next test selection from a database. Also tests is resultset is empty.
		SelectFirstLine();
		
	}

}
