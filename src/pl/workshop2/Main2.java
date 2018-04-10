package pl.workshop2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class Main2 {
	
	
	//admin menu for managing Excercises
	public static void main(String[] args) {
		
		adminMenuExcercise();		
		
	}


	protected static void adminMenuExcercise() {
		
		Scanner scan = new Scanner(System.in);
		
				
		try (Connection conn = DbUtil.getConn()) {
		
			System.out.println("Excercises list: ");
			System.out.println(ArrayUtils.toString(Excercise.loadAllEx(conn)));	
				
			System.out.println("Choose option: 'add' 'edit' 'delete' for excercises, or 'quit' to end :");
			String command = scan.nextLine();
			
			while(!command.equals("quit")) {
				
				
				try {
					
					if (command.equals("add")) {
					
						System.out.println("Excercise title: ");
						String title = scan.nextLine();
						
						System.out.println("Description for " + title + " :");
						String description = scan.nextLine();						
					
						Excercise exec = new Excercise(title, description);
						
						exec.saveToDB(conn);				
					}
					
					else if (command.equals("edit")) {
						
						System.out.println("Excercise Id to edit:");
						int exId = scan.nextInt();
						scan.nextLine();
						
						System.out.println("New title: ");
						String titleEdit = scan.nextLine();
						
						System.out.println("New description for " + titleEdit + " :");
						String descEdit = scan.nextLine();
						
						Excercise editEx= Excercise.loadExById(conn, exId);
						
						editEx.setTitle(titleEdit);
						editEx.setDescription(descEdit);
						
						editEx.saveToDB(conn);
					}
					
					else if (command.equals("delete")) {
						
						System.out.println("Excercise Id to delete:");
						int exId = scan.nextInt();
						
						Excercise delEx = Excercise.loadExById(conn, exId);
						
						delEx.delete(conn);				
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
				
				
				
				System.out.println("Excercises list: ");
				System.out.println(ArrayUtils.toString(Excercise.loadAllEx(conn)));	
					
				System.out.println("Choose option: 'add' 'edit' 'delete' for excercises, or 'quit' to end :");
				command = scan.next();
			}
								
			scan.close();
		}
							    
		 catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}

	
