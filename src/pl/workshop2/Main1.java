package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main1 {
	
	
	
	public static void main(String[] args) {
		
				
		User user = new User("Stefan", "qwerty", "stefffo@gmail.com");
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop2_ex?useSSL=false",
                "root", "coderslab")) {
		
				user.saveToDB(conn);
				
				
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}


