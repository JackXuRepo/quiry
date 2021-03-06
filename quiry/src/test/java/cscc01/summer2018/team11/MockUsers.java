package cscc01.summer2018.team11;


import java.sql.SQLException;

import cscc01.summer2018.team11.database.UserDAO;
import cscc01.summer2018.team11.user.Admin;
import cscc01.summer2018.team11.user.Instructor;
import cscc01.summer2018.team11.user.Student;


/**
 * Mock User Objects. Run this to populate User database.
 * http://www.randompassages.com/
 */
public class MockUsers {

    public static Admin user1() {
        String userId = "hallsara";
        String firstName = "Sarah";
        String lastName = "Hall";
        String email = "hallsara@mail.utoronto.ca";
        String password = "password";
        String verification = "activated";
        return new Admin(userId, email, password, firstName, lastName, verification);
    }

    public static Instructor user2() {
        String userId = "houelleb";
        String firstName = "Michel";
        String lastName = "Houellebecq";
        String email = "houelleb@mail.utoronto.ca";
        String password = "password";
        String verification = "activated";
        return new Instructor(userId, email, password, firstName, lastName, verification);
    }


    public static Instructor user3() {
        String userId = "mitchell";
        String firstName = "David";
        String lastName = "Mitchell";
        String email = "mitchell@mail.utoronto.ca";
        String password = "password";
        String verification = "activated";
        return new Instructor(userId, email, password, firstName, lastName, verification);
    }

    public static Student user4() {
        String userId = "mantelhi";
        String firstName = "Hilary";
        String lastName = "Mantel";
        String email = "mantelhi@mail.utoronto.ca";
        String password = "password";
        String verification = "activated";
        return new Student(userId, email, password, firstName, lastName, verification);
    }

    public static Student user5() {
        String userId = "ferrisjo";
        String firstName = "Joshua";
        String lastName = "Ferris";
        String email = "ferrisjo@mail.utoronto.ca";
        String password = "password";
        String verification = "activated";
        return new Student(userId, email, password, firstName, lastName, verification);
    }

    public static void main(String[] args) throws SQLException {
        UserDAO userDao = new UserDAO();
        userDao.updateUser( user1() );
        userDao.updateUser( user2() );
        userDao.updateUser( user3() );
        userDao.updateUser( user4() );
        userDao.updateUser( user5() );

        for (String id : userDao.getAllUserIds()) {
            System.out.println(id + " : " + userDao.getUserByUserId(id));
        }
        userDao.close();
    }

}
