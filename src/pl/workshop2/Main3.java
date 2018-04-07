package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

public class Main3 {
	
	
	
	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop2_ex?useSSL=false",
                "root", "coderslab")) {
				
				System.out.println(Arrays.toString(User.loadAllUsers(conn)));
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}
		