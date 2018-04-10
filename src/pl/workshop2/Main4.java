package pl.workshop2;

import java.sql.Connection;

import org.apache.commons.lang3.ArrayUtils;

public class Main4 {
	
	
	//add new ex to database using constructor (also possible by setters)
	public static void main(String[] args) {
		
				
		try (Connection c = DbUtil.getConn()) {
			
			Solution[] s = new Solution[0];
			s=Solution.loadAllByExerciseId(c, 2);
			
			
			System.out.println(ArrayUtils.toString(s));
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
	
}