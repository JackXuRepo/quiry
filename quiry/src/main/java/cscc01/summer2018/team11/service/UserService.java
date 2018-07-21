package cscc01.summer2018.team11.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import cscc01.summer2018.team11.database.UserDAO;
import cscc01.summer2018.team11.user.AccessLevel;
import cscc01.summer2018.team11.user.Admin;
import cscc01.summer2018.team11.user.Instructor;
import cscc01.summer2018.team11.user.Student;
import cscc01.summer2018.team11.user.User;

@Service
public class UserService {
    private static User generateUser(HashMap<String, String> userData) {

        String userId = userData.get("userId");
        String firstName = userData.get("firstName");
        String lastName = userData.get("lastName");
        String email = userData.get("email");
        String password = userData.get("password");
        int accessLevel = Integer.parseInt(userData.get("accessLevel"));

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
    
    
    public static boolean createUser(HashMap<String, String> userData) {
    	User newUser = generateUser(userData);
    	boolean userExist;
    	try {
			UserDAO userDb = new UserDAO();
			userExist = !(userDb.getUserByUserId(userData.get("userId")) == null);
			if(!userExist) {
				userDb.updateUser(newUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
    	return userExist;
    }
}
