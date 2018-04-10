package pl.workshop2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	
	protected int id;
    protected String name;
    
    
    
	public Group(String name) {
		
		this.name = name;
	}
	
	public Group() {
		
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * Method saves new/updates existing record to database
	 * @param conn
	 * @throws SQLException
	 */
	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO user_group (name) VALUES (?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.name);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    	}
		  } else {
			    String sql1 = "UPDATE user_group SET name=? where id = ?";
			    PreparedStatement preparedStatement1;
			    preparedStatement1 = conn.prepareStatement(sql1);
			    preparedStatement1.setString(1, this.name);
			    preparedStatement1.setInt(2, this.id);
			    preparedStatement1.executeUpdate();
			}
		  }
	
	/**
	 * 
	 * @param conn
	 * @param id
	 * @return Group with given Id
	 * @throws SQLException
	 */
	static public Group loadGroupById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM user_group where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        Group loadedGroup = new Group();
	        loadedGroup.id = resultSet.getInt("id");
	        loadedGroup.name = resultSet.getString("username");
	        return loadedGroup;}
	    return null;}
    
	/**
	 * 
	 * @param conn
	 * @return All groups as an Array
	 * @throws SQLException
	 */
	static public Group[] loadAllGroups(Connection conn) throws SQLException {
	    ArrayList<Group> groups = new ArrayList<Group>();
	    String sql = "SELECT * FROM user_group"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        Group loadedGroup = new Group();
	        loadedGroup.id = resultSet.getInt("id");
	        loadedGroup.name = resultSet.getString("name");
	        groups.add(loadedGroup);}
	    Group[] uArray = new Group[groups.size()]; uArray = groups.toArray(uArray);
	    return uArray;}
	
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM user_group WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}
	
	
    
}

