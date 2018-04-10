package pl.workshop2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    protected int id;
    protected String username;
    protected String password;
    protected String email;
    protected int userGroupId;
	
    public User(String username, String password, String email, int userGroupId) {
		this.username = username;
		this.email = email;
		this.setPassword(password);
		this.userGroupId = userGroupId;		
	}
    
    public User() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	
	public int getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO Users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.username);
		    preparedStatement.setString(2, this.email);
		    preparedStatement.setString(3, this.password);
		    preparedStatement.setInt(4, this.userGroupId);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    	}
		  } else {
			    String sql1 = "UPDATE Users SET username=?, email=?, password=?, user_group_id=? where id = ?";
			    PreparedStatement preparedStatement1;
			    preparedStatement1 = conn.prepareStatement(sql1);
			    preparedStatement1.setString(1, this.username);
			    preparedStatement1.setString(2, this.email);
			    preparedStatement1.setString(3, this.password);
			    preparedStatement1.setInt(4, this.userGroupId);
			    preparedStatement1.setInt(5, this.id);
			    preparedStatement1.executeUpdate();
			}
		  }
		  
	
		
	
	static public User loadUserById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM Users where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        User loadedUser = new User();
	        loadedUser.id = resultSet.getInt("id");
	        loadedUser.username = resultSet.getString("username");
	        loadedUser.password = resultSet.getString("password");
	        loadedUser.email = resultSet.getString("email");
	        loadedUser.userGroupId = resultSet.getInt("user_group_id");
	        return loadedUser;}
	    return null;}
	
	

	static public User[] loadAllUsers(Connection conn) throws SQLException {
	    ArrayList<User> users = new ArrayList<User>();
	    String sql = "SELECT * FROM Users"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        User loadedUser = new User();
	        loadedUser.id = resultSet.getInt("id");
	        loadedUser.username = resultSet.getString("username");
	        loadedUser.password = resultSet.getString("password");
	        loadedUser.email = resultSet.getString("email");
	        loadedUser.userGroupId = resultSet.getInt("user_group_id");
	        users.add(loadedUser);}
	    User[] uArray = new User[users.size()]; uArray = users.toArray(uArray);
	    return uArray;}
	
	
	
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM Users WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	}
	
	
	static public User[] loadAllByGrupId(Connection conn, int id) throws SQLException {
	    ArrayList<User> users = new ArrayList<User>();
	    String sql = "SELECT * FROM Users where user_group_id=?"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        User loadedUser = new User();
	        loadedUser.id = resultSet.getInt("id");
	        loadedUser.username = resultSet.getString("username");
	        loadedUser.password = resultSet.getString("password");
	        loadedUser.email = resultSet.getString("email");
	        loadedUser.userGroupId = resultSet.getInt("user_group_id");
	        users.add(loadedUser);}
	    User[] uArray = new User[users.size()]; uArray = users.toArray(uArray);
	    return uArray;}
	

	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email
				+ ", userGroupId=" + userGroupId + "] \n";
	}
	
	
	
	
}

