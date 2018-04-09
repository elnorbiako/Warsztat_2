package pl.workshop2;

import java.sql.Connection;

public class Main1 {
	
	
	//add new user to database using setters (also possible by constructor)
	public static void main(String[] args) {
		
				
		try (Connection c = DbUtil.getConn()) {
			
		User user = new User(); 
		user.setUsername("Wacek");
		user.setEmail("waco1@yahoo.com");
		user.setPassword("kilof");
		
		user.saveToDB(c);
		
						
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}


