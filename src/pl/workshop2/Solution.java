package pl.workshop2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {
	
	protected int id;
    protected String created;
    protected String updated;
    protected String description;
    protected int excerciseId;
    protected int usersId;
    
    
	public Solution(String description, int excerciseId, int usersId) {
		super();
		this.description = description;
		this.excerciseId = excerciseId;
		this.usersId = usersId;
	}


	public Solution() {
		
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public String getUpdated() {
		return updated;
	}


	public void setUpdated(String updated) {
		this.updated = updated;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getExcerciseId() {
		return excerciseId;
	}


	public void setExcerciseId(int excerciseId) {
		this.excerciseId = excerciseId;
	}


	public int getUsersId() {
		return usersId;
	}


	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}


	public int getId() {
		return id;
	}
	
	
	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO solution (description, created, users_id, excercise_id) VALUES (?, NOW(), ?, ?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.description);
		    preparedStatement.setLong(2, this.usersId);
		    preparedStatement.setLong(3, this.excerciseId);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    	}
		  } else {
			    String sql1 = "UPDATE solution SET description=?, users_id=?, excercise_id=?, updated=NOW() where id=?";
			    PreparedStatement preparedStatement1;
			    preparedStatement1 = conn.prepareStatement(sql1);
			    preparedStatement1.setString(1, this.description);
			    preparedStatement1.setInt(2, this.usersId);
			    preparedStatement1.setInt(3, this.excerciseId);
			    preparedStatement1.setInt(4, this.id);
			    preparedStatement1.executeUpdate();
			}
		  }
		  
	
	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM solution where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        Solution loadedSolution = new Solution();
	        loadedSolution.id = resultSet.getInt("id");
	        loadedSolution.description = resultSet.getString("description");
	        loadedSolution.created = resultSet.getString("created");
	        loadedSolution.updated = resultSet.getString("updated");
	        loadedSolution.excerciseId = resultSet.getInt("excercise_id");
	        loadedSolution.usersId = resultSet.getInt("users_id");
	        return loadedSolution;}
	    return null;}
	
	
	
	static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
	    ArrayList<Solution> sols = new ArrayList<Solution>();
	    String sql = "SELECT * FROM solution"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        Solution loadedSolution = new Solution();
	        loadedSolution.id = resultSet.getInt("id");
	        loadedSolution.description = resultSet.getString("description");
	        loadedSolution.created = resultSet.getString("created");
	        loadedSolution.updated = resultSet.getString("updated");
	        loadedSolution.usersId = resultSet.getInt("users_id");
	        loadedSolution.excerciseId = resultSet.getInt("excercise_id");
	        sols.add(loadedSolution);}
	    Solution[] uArray = new Solution[sols.size()]; uArray = sols.toArray(uArray);
	    return uArray;}
	
	
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM solution WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	    
	}

	@Override
	public String toString() {
		return "Solution [id=" + id + ", created=" + created + ", updated=" + updated + ", description=" + description
				+ ", excerciseId=" + excerciseId + ", usersId=" + usersId + "]";
	}
	
	    

}
