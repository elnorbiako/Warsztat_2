package pl.workshop2;

import java.sql.Connection;

public class Main1 {
	
	
	//add new user to database using setters (also possible by constructor)
	public static void main(String[] args) {
		
				
		try (Connection c = DbUtil.getConn()) {
			
		Solution solo = new Solution("test solution3", 2, 3);
		
		solo.saveToDB(c);
		
						
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}


