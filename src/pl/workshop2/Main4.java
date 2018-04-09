package pl.workshop2;

import java.sql.Connection;

public class Main4 {
	
	
	//add new ex to database using constructor (also possible by setters)
	public static void main(String[] args) {
		
				
		try (Connection c = DbUtil.getConn()) {
			
			Excercise exce = new Excercise("Excercise 3", "test ex 3, so far so good");
			exce.saveToDB(c);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
	
}