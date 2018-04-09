package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main1 {
	
	
	
	public static void main(String[] args) {
		
				
		
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop2_ex?useSSL=false",
                "root", "coderslab")) {
			
		User user = User.loadUserById(conn, 1);
		user.delete(conn);
				
				
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}


