import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	public static Connection Connect() throws ClassNotFoundException, SQLException
	{
		//create a mySQL database connection
		String myDriver = "org.gjt.mm.mysql.driver";
		String myUrl = "jdbc:mysql://localhost/test";
		//String myUrl = "jdbc:mysql://localhost:3306";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "root", "SequelCity2000");
		return conn;
	}
}
