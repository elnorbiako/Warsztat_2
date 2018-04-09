package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility for getting a connection with database
 * @author norbiak
 *
 */
public class DbUtil {
    private static String DB_URL = "jdbc:mysql://localhost:3306/workshop2_ex?useSSL=false";
	private static String DB_USER = "root";
	private static String DB_PASS = "coderslab";
	
	public static Connection getConn() throws SQLException {
	return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
	

}
