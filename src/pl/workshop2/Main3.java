package pl.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class Main3 {
	
	
	//print all users from table (using toString) 
	public static void main(String[] args) {
		
		try (Connection c = DbUtil.getConn()) {
				
				User[] toModify = new User[0];
				toModify=User.loadAllByGrupId(c, 3);
		//		toModify.setUserGroupId(3);
		//		toModify.saveToDB(c);
				
				System.out.println(ArrayUtils.toString(toModify));
				
				
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}
		