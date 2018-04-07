package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main2 {
	
	
	
	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop2_ex?useSSL=false",
                "root", "coderslab")) {
				
				System.out.println(User.loadUserById(conn, 1));
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}
		