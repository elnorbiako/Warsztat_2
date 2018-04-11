package pl.workshop2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class Main4 {
	
	
	//admin menu for managing solutions
	public static void main(String[] args) {
		
		adminMenuSolution();		
		
	}


	protected static void adminMenuSolution() {
		
		Scanner scan = new Scanner(System.in);
		
				
		try (Connection conn = DbUtil.getConn()) {
		
							
			System.out.println("Choose option: 'add' 'view' for solution, or 'quit' to end :");
			String command = scan.nextLine();
			
			while(!command.equals("quit")) {
				
				try {
					
					if (command.equals("add")) {
					
						System.out.println("User list: ");
						System.out.println(ArrayUtils.toString(User.loadAllUsers(conn)));
						
						System.out.println("Chose user Id: ");
						int userId = scan.nextInt();
						scan.nextLine();
						
						System.out.println("Excercises list: ");
						System.out.println(ArrayUtils.toString(Excercise.loadAllEx(conn)));	
						
						System.out.println("Chose excercise Id: ");
						int exId = scan.nextInt();
						scan.nextLine();
						
						
						Solution solution = new Solution();
						solution.setUsersId(userId);
						solution.setExcerciseId(exId);
												
						solution.saveToDB(conn);				
					}
					
					else if (command.equals("view")) {
						
						System.out.println("User list: ");
						System.out.println(ArrayUtils.toString(User.loadAllUsers(conn)));
						
						System.out.println("User Id to view solutions:");
						int userId = scan.nextInt();
						scan.nextLine();
						
						System.out.println(ArrayUtils.toString(Solution.loadAllByUserId(conn, userId)));
					}
					
					else {
						System.out.println("Please try again");}
											
				} catch (SQLException e) {
				System.out.print("Incorrect input: " + e.getMessage());
				
				} catch (NumberFormatException e) {
					System.out.println("Not a number: " + e.getMessage());
				}
				
									
				System.out.println("Choose option: 'add' 'view' for solutions, or 'quit' to end :");
				command = scan.nextLine();
			}
								
			scan.close();
		}
							    
		 catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}

	
