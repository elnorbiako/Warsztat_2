package pl.workshop2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class Main3 {
	
	
	//admin menu for managing groups
	public static void main(String[] args) {
		
		adminMenuGroup();		
		
	}


	protected static void adminMenuGroup() {
		
		Scanner scan = new Scanner(System.in);
		
				
		try (Connection conn = DbUtil.getConn()) {
		
			System.out.println("Group list: ");
			System.out.println(ArrayUtils.toString(Group.loadAllGroups(conn)));	
				
			System.out.println("Choose option: 'add' 'edit' 'delete' for groups, or 'quit' to end :");
			String command = scan.nextLine();
			
			while(!command.equals("quit")) {
				
				
				try {
					
					if (command.equals("add")) {
					
						System.out.println("Group name: ");
						String name = scan.nextLine();
						
						
						Group group = new Group(name);
						
						group.saveToDB(conn);				
					}
					
					else if (command.equals("edit")) {
						
						System.out.println("Group Id to edit:");
						int groupId = scan.nextInt();
						scan.nextLine();
						
						System.out.println("New Id :");
						int editId = scan.nextInt();
						scan.nextLine();
						
						System.out.println("New name: ");
						String nameEdit = scan.nextLine();
						
						
						
						Group editGroup= Group.loadGroupById(conn, groupId);
						
						editGroup.setName(nameEdit);
						editGroup.setId(editId);
						
						editGroup.saveToDB(conn);
					}
					
					else if (command.equals("delete")) {
						
						System.out.println("Group Id to delete:");
						int groupId = scan.nextInt();
						scan.nextLine();
						
						Group delGroup = Group.loadGroupById(conn, groupId);
						
						delGroup.delete(conn);				
					}
					
					else {
						System.out.println("Please try again");}
											
				} catch (SQLException e) {
				System.out.print("Incorrect input: " + e.getMessage());
				
				} catch (NumberFormatException e) {
					System.out.println("Not a number: " + e.getMessage());
				}
				
				
				
				System.out.println("Group list: ");
				System.out.println(ArrayUtils.toString(Group.loadAllGroups(conn)));	
					
				System.out.println("Choose option: 'add' 'edit' 'delete' for groups, or 'quit' to end :");
				command = scan.nextLine();
			}
								
			scan.close();
		}
							    
		 catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}

	
