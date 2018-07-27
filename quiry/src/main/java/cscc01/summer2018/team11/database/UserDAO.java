package cscc01.summer2018.team11.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import cscc01.summer2018.team11.user.AccessLevel;
import cscc01.summer2018.team11.user.Admin;
import cscc01.summer2018.team11.user.Instructor;
import cscc01.summer2018.team11.user.Student;
import cscc01.summer2018.team11.user.User;


public class UserDAO {

    private Connection c;
    private Statement stmt;

    public UserDAO() throws SQLException {
        this.c = Database.getConnection();
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
        String sql = "INSERT OR REPLACE INTO User (userId, firstName, lastName,"
                + " email, password, accesslvl)"
                + " VALUES (?,?,?,?,?,?);";
        PreparedStatement stmt = c.prepareStatement(sql);

        int i = 1;
        stmt.setString(i++, userData.getUserId());
        stmt.setString(i++, userData.getFirstName());
        stmt.setString(i++, userData.getLastName());
        stmt.setString(i++, userData.getEmail());
        stmt.setString(i++, userData.getPassword());
        stmt.setInt(i++, userData.getAccessLv());
        stmt.executeUpdate();
    }

    public void deleteUser(String userId) throws SQLException {
        String sql = "DELETE FROM User WHERE userId=" + userId + ";";
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
          case AccessLevel.STUDENT:
              return new Student(userId, email, password, firstName, lastName);
          case AccessLevel.INSTRUCTOR:
              return new Instructor(userId, email, password, firstName, lastName);
          case AccessLevel.ADMIN:
              return new Admin(userId, email, password, firstName, lastName);
          default:
              return null;
        }
    }

    public User getUserByUserId(String idUser) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE userId='" + idUser + "';");
        if ( rs.next() ) {
            return generateUser(rs);
        }
        return null;
    }

    public Set<String> getAllUserIds() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT userId FROM User;");
        HashSet<String> userIdSet = new HashSet<>();

        while ( rs.next() ) {
            userIdSet.add( rs.getString(1) );
        }
        return userIdSet;
    }

}