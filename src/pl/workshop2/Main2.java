package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main2 {	
	
	//modify user: 1st load by ID, modify email, save to database
	public static void main(String[] args) {
		
		try (Connection conn = DbUtil.getConn()) {
				
				User toModify = new User();
				toModify=User.loadUserById(conn, 2);
				toModify.setEmail("zdzislaw_pierwszy@lalamido.pl");
				toModify.saveToDB(conn);
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}
		