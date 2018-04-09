package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

public class Main3 {
	
	
	//print all users from table (using toString) 
	public static void main(String[] args) {
		
		try (Connection c = DbUtil.getConn()) {
				
				System.out.println(Arrays.toString(User.loadAllUsers(c)));
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}
		