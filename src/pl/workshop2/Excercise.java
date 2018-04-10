package pl.workshop2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Excercise {
	
	protected int id;
    protected String title;
    protected String description;
	
    
    
    public Excercise(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Excercise() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * Method that save new/update existing record in database  
	 * @param conn
	 * @throws SQLException
	 */
	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO excercise (title, description) VALUES (?, ?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.title);
		    preparedStatement.setString(2, this.description);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    	}
		  } else {
			    String sql1 = "UPDATE excercise SET title=?, description=? where id = ?";
			    PreparedStatement preparedStatement1;
			    preparedStatement1 = conn.prepareStatement(sql1);
			    preparedStatement1.setString(1, this.title);
			    preparedStatement1.setString(2, this.description);
			    preparedStatement1.setInt(3, this.id);
			    preparedStatement1.executeUpdate();
			}
		  }
		  
	
		
	/**
	 * 
	 * @param conn
	 * @param id
	 * @return Exercise with given id
	 * @throws SQLException
	 */
	static public Excercise loadExById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM excercise where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        Excercise loadedEx = new Excercise();
	        loadedEx.id = resultSet.getInt("id");
	        loadedEx.title = resultSet.getString("title");
	        loadedEx.description = resultSet.getString("description");
	        return loadedEx;}
	    return null;}
	
	
	/**
	 * 
	 * @param conn
	 * @return All excercises as an Array
	 * @throws SQLException
	 */
	static public Excercise[] loadAllEx(Connection conn) throws SQLException {
	    ArrayList<Excercise> exces = new ArrayList<Excercise>();
	    String sql = "SELECT * FROM excercise"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        Excercise loadedEx = new Excercise();
	        loadedEx.id = resultSet.getInt("id");
	        loadedEx.title = resultSet.getString("title");
	        loadedEx.description = resultSet.getString("description");
	        exces.add(loadedEx);}
	    Excercise[] uArray = new Excercise[exces.size()]; uArray = exces.toArray(uArray);
	    return uArray;}
	
	
	/**
	 * Method deletes a loaded excersice 
	 * @param conn
	 * @throws SQLException
	 */
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM excercise WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	}

	@Override
	public String toString() {
		return "Excercise [id=" + id + ", title=" + title + ", description=" + description + "] \n";
	}
	
	

}


	




	
	
    
    
	

    
    

