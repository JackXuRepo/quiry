package cscc01.summer2018.team11.user;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDatabase {
	/**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewUserDatabase() {
    	// for now lets create the local database in the home directory
        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
             	+ "	firstName text NOT NULL,\n"
             	+ "	lastName text NOT NULL,\n"
             	+ "	email text NOT NULL,\n"
             	+ "	password text NOT NULL,\n"
             	+ "	accesslvl int NOT NULL,\n"
             	+ "	image BLOB NOT NULL\n"
             	+ ");";
 
        try (Connection conn = connect()) {
           /* if (conn != null) {
                //DatabaseMetaData meta = conn.getMetaData();
            }
 */			
        	java.sql.Statement state = conn.createStatement();
        	state.execute(sql);
        	
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
      }
    }
    /**
     * Use email address as the primary key
     * @throws SQLException 
     */
    public static void addUser(String firstName, String lastName, String email, String password, int accesslvl, Blob image) throws SQLException{
    	String addRow = "INSERT INTO Users(firstName, lastName, email, password, accesslvl, image) VALUES(?,?,?,?,?,?)";
    	Connection conn = connect();
    	PreparedStatement state = conn.prepareStatement(addRow);
    	state.setString(1, firstName);
    	state.setString(2, lastName);
    	state.setString(3, email);
    	state.setString(4, password);
    	state.setInt(5, accesslvl);
    	state.setBlob(6, image);
    	state.execute(addRow);
    	
    }
    
    /**
     * helper to start the connection
     */
    private static Connection connect(){
    	String url = "jdbc:sqlite:C:/User";
    	Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }




}


    
