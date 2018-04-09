package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

public class Main2 {	
	
	//modify user: 1st load by ID, modify email, save to database
	public static void main(String[] args) {
		
		try (Connection conn = DbUtil.getConn()) {
				
				Solution[] toModify = new Solution[0];
				toModify=Solution.loadAllSolutions(conn);
	//			toModify.setDescription("this is a test of modifying desc in solution, mainly for update date");
	//			toModify.saveToDB(conn);
	
				System.out.println(Arrays.toString(toModify));
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}
		