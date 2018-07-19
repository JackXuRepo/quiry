package cscc01.summer2018.team11.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cscc01.summer2018.team11.user.Instructor;
import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.Student;


public class UserDAO {

    private Connection c;
    private Statement stmt;

    public UserDAO(Connection c) throws SQLException {
        this.c = c;
        this.stmt = c.createStatement();
    }

    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public void updateUser(User userData) throws SQLException {
        String sql = "INSERT INTO User (userId, firstName, lastName,"
                + " email, password, accesslvl)"
                + " VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE;";
        PreparedStatement stmt = c.prepareStatement(sql);

        int i = 1;
        stmt.setString(i++, userData.getUserId());
        stmt.setString(i++, userData.getFirstName());
        stmt.setString(i++, userData.getLastName());
        stmt.setString(i++, userData.getEmail());
        stmt.setString(i++, userData.getPassword());
        stmt.setInt(i++, Integer.parseInt(userData.getAccessLv()+""));
        stmt.executeUpdate();
    }

    public void deleteUser(String userId) throws SQLException {
        String sql = "DROP * FROM User WHERE userId=" + userId + ";";
        
        stmt.executeUpdate(sql);
    }

    public void close() throws SQLException {
        stmt.close();
    }

    private static User generateUser(ResultSet rs) throws SQLException {
        int i = 1;

        String userId = rs.getString(i++);
        String firstName = rs.getString(i++);
        String lastName = rs.getString(i++);
        String email = rs.getString(i++);
        String password = rs.getString(i++);
        int accessLevel = rs.getInt(i++);
        
        switch(accessLevel) {
          case 1: 
            return new Student(userId, firstName, lastName, email, password);
          case 2:
            return new Instructor(userId, firstName, lastName, email, password);
          default: 
            return null;
        }

    }

    public User getDataByUserId(String idUser) throws SQLException{
        ResultSet rs = stmt.executeQuery( "SELECT * FROM User WHERE userId=" + idUser + ";");
        if ( rs.next() ) {
            return generateUser(rs);
        }
        return null;
    }
    



}