package cscc01.summer2018.team11.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private Connection dbConnection;

    public DbConnection() throws SQLException {
        this.dbConnection = connectToDatabase("quiry");
        createNewUserTable();
        createNewFileTable();
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
                + "userId text PRIMARY KEY,\n"
                + "firstName text NOT NULL,\n"
                + "lastName text NOT NULL,\n"
                + "email text NOT NULL,\n"
                + "password text NOT NULL,\n"
                + "accesslvl int NOT NULL\n"
                + ");";

        dbConnection.createStatement().execute(sql);
    }

    public void createNewFileTable() throws SQLException {
        // for now lets create the local database in the home directory
        String sql = "CREATE TABLE IF NOT EXISTS File (\n"
                + "fileId int PRIMARY KEY\n,"
                + "userId text NOT NULL,\n"
                + "fileType int NOT NULL,\n"
                + "contentType int NOT NULL,\n"
                + "accesslvl int NOT NULL,\n"
                + "title text NOT NULL,\n"
                + "course text NOT NULL,\n"
                + "courseRestricted int NOT NULL,\n"
                + "filePath text NOT NULL,\n"
                + "description text NOT NULL,\n"
                + "uploadMs int NOT NULL,\n"
                + "FOREIGN KEY (userId) REFERENCES User (userId)\n"
                + "ON DELETE CASCADE ON UPDATE NO ACTION\n"
                + ");";

        dbConnection.createStatement().execute(sql);
    }

    public Connection getConnection() {
        return this.dbConnection;
    }

}
