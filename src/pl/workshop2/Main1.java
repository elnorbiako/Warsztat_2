package pl.workshop2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class Main1 {
	
	
	//add new user to database using setters (also possible by constructor)
	public static void main(String[] args) {
		
		adminMenuUsers();		
		
	}


	protected static void adminMenuUsers() {
		
		Scanner scan = new Scanner(System.in);
		
				
		try (Connection conn = DbUtil.getConn()) {
		
			System.out.println("User list: ");
			System.out.println(ArrayUtils.toString(User.loadAllUsers(conn)));	
				
			System.out.println("Choose option: 'add' 'edit' 'delete' for users, or 'quit' to end :");
			String command = scan.next();
			
			while(!command.equals("quit")) {
				
				
				try {
					
					if (command.equals("add")) {
					
						System.out.println("New user name:");
						String username = scan.nextLine();
						
						System.out.println("Password for " + username + " :");
						String password = scan.next();
						
						System.out.println("Email for " + username + " :");
						String email = scan.next();
						
						System.out.println("Set group id for " + username + " :");
						int userGroupId = scan.nextInt();
						scan.nextLine();
						
						User user= new User(username, password, email, userGroupId);
						
						user.saveToDB(conn);				
					}
					
					else if (command.equals("edit")) {
						
						System.out.println("User Id to edit:");
						int userId = scan.nextInt();
						scan.nextLine();
						
						System.out.println("New username: ");
						String usernameEdit = scan.nextLine();
						
						System.out.println("New password for " + usernameEdit + " :");
						String password = scan.next();
						
						System.out.println("New email for " + usernameEdit + " :");
						String email = scan.next();
						
						System.out.println("New group id for " + usernameEdit + " :");
						int userGroupId = scan.nextInt();
						scan.nextLine();
						
						User editUser= User.loadUserById(conn, userId);
						
						editUser.setUsername(usernameEdit);
						editUser.setPassword(password);
						editUser.setEmail(email);
						editUser.setUserGroupId(userGroupId);
						
						editUser.saveToDB(conn);
					}
					
					else if (command.equals("delete")) {
						
						System.out.println("User Id to delete:");
						int userId = scan.nextInt();
						
						User delUser = User.loadUserById(conn, userId);
						
						delUser.delete(conn);				
					}
					
					else 
			//			if (!command.equals("add")  && !command.equals("edit") && !command.equals("delete"))
						{
						System.out.println("Please try again");
						}
											
				} catch (SQLException e) {
				System.out.print("Incorrect input: " + e.getMessage());
				
				} catch (NumberFormatException e) {
					System.out.println("Not a number: " + e.getMessage());
				}
				
				
				
				System.out.println("User list: ");
				System.out.println(ArrayUtils.toString(User.loadAllUsers(conn)));	
					
				System.out.println("Choose option: 'add' 'edit' 'delete' for users, or 'quit' to end :");
				command = scan.next();
			}
								
			scan.close();
		}
							    
		 catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}

	
