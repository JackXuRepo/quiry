package cscc01.summer2018.team11.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	
	private Connection dbConnection;

	
	public DatabaseConnection() throws SQLException {
		this.dbConnection = connectToDatabase("quiry");
		createNewUserTable();

	}
	
	
	public Connection connectToDatabase(String dbName) {
		String url = "jdbc:sqlite:C:" + dbName +".db";
    	Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
	}
	
	
    public void createNewUserTable() throws SQLException {
    	// for now lets create the local database in the home directory
        String sql = "CREATE TABLE IF NOT EXISTS User (\n"
             	+ "	userId text PRIMARY KEY,\n"
             	+ "	firstName text NOT NULL,\n"
             	+ "	lastName text NOT NULL,\n"
             	+ "	email text NOT NULL,\n"
             	+ "	password text NOT NULL,\n"
             	+ "	accesslvl int NOT NULL,\n"
             	+ ");";
 

        dbConnection.createStatement().execute(sql);

    }
    


    public Connection getConnection() {
    	return this.dbConnection;
    }

}
