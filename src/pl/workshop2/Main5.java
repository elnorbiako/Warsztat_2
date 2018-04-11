// work in progress - main menu for admins

/*
package pl.workshop2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import pl.workshop2.DbUtil;


public class Main5 {




	protected static void adminMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		try (Connection conn = DbUtil.getConn()) {
		
							
			System.out.println("Choose menu: 'users' 'group' 'excercise' 'solution', or 'quit' to end :");
			String command = scan.nextLine();
			
			while(!command.equals("quit")) {
				
				try {
					
					if (command.equals("users")) {
					
						adminMenuUsers();
						
					}
					
					else if (command.equals("group")) {
						
						adminMenuGroup();
					}
					
					else if (command.equals("excercise")) {
						
						adminMenuExcercise();
					}
					
					else if (command.equals("solution")) {
						
						adminMenuSolution();
					}
					
					else	{
						System.out.println("Please try again");
					}
					scan.close();
				}
									    
				 catch (Exception e) {
					System.out.print(e.getMessage());
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}


*/