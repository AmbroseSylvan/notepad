import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLcommands {
	
	private static Connection Connect(String Schema) throws ClassNotFoundException, SQLException
	{
		//create a mySQL database connection
		String myUrl = "jdbc:mysql://localhost/Journal";// + Schema;
		Connection conn = DriverManager.getConnection(myUrl, "root", "SequelCity2000");
		return conn;
	}
	
	public static String Select(String Schema, String Text)
	{
		String Query = Text;
		String Result = null;
		
		try
		{
			Connection conn = Connect(Schema);
			PreparedStatement preparedStmt = conn.prepareStatement(Query);
			ResultSet rs = preparedStmt.executeQuery(Query);
			if (rs.next())
			{
				Result = rs.getString(1);
			}
			conn.close();
			return Result;
		} catch (Exception e)
		{
			System.err.println("Got an exception");
			System.err.println(e.getMessage());
		}
		return Result;
	}
	
	public static void Insert(String Schema, String Text)
	{
		String Query = Text;
		
		try
		{
			Connection conn = Connect(Schema);
			PreparedStatement preparedStmt = conn.prepareStatement(Query);
			preparedStmt.execute();
			conn.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception");
			System.err.println(e.getMessage());
		}

	}

	public static void Update(String Schema, String Text) {
		String Query = Text;
		
		try 
		{
			Connection conn = Connect(Schema);
			PreparedStatement preparedStmt = conn.prepareStatement(Query);
			preparedStmt.execute();
			conn.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception");
			System.err.println(e.getMessage());
		}
		
	}

}
