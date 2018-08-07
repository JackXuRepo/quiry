package cscc01.summer2018.team11.user;


import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import cscc01.summer2018.team11.database.UserDAO;


@Service
public class UserService {

    public static User getUser(String userId) {
        User userInfo = null;
        try {
            UserDAO userDb = new UserDAO();
            userInfo = userDb.getUserByUserId(userId);

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return userInfo;
    }

    public static boolean addUser(User user) {
        String userId = user.getUserId();
        if ( existUser(userId) ) {
            return false;
        }

        return updateUser(user);
    }

    public static boolean updateUser(User user) {
        try {
            UserDAO userDb = new UserDAO();
            userDb.updateUser(user);

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteUser(String userId) {
        try {
            UserDAO userDb = new UserDAO();
            userDb.deleteUser(userId);
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean existUser(String userId) {
        boolean exist = false;
        try {
            UserDAO userDb = new UserDAO();
            exist = !(userDb.getUserByUserId(userId) == null);

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return exist;
    }

    private static User generateUser(HashMap<String, String> userData) {
        String userId = userData.get("userId");
        String firstName = userData.get("firstName");
        String lastName = userData.get("lastName");
        String email = userData.get("email");
        String password = userData.get("password");
        int accessLevel = Integer.parseInt(userData.get("accessLevel"));
        String verification = userData.get("verification");

        switch(accessLevel) {
        case AccessLevel.STUDENT:
            return new Student(userId, email, password, firstName, lastName, verification);
        case AccessLevel.INSTRUCTOR:
            return new Instructor(userId, email, password, firstName, lastName, verification);
        case AccessLevel.ADMIN:
            return new Admin(userId, email, password, firstName, lastName, verification);
        default:
            return null;
        }
    }

    public static boolean createUser(HashMap<String, String> userData) {
        User newUser = generateUser(userData);
        boolean userExist;

        try {
            UserDAO userDb = new UserDAO();
            userExist = existUser(newUser.getUserId());

            if (!userExist) {
                userDb.updateUser(newUser);
            }

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return false;
        }
        return !userExist;
    }

    public static User loginUser(HashMap<String, String> userData) {
        User currUser = getUser(userData.get("userId"));
        if (currUser == null || !userData.get("password").equals(currUser.getPassword())) {
            return null;
        }

        return currUser;
    }

    public static String generateVerificationCode() {
        String validChars = "01234567890qwertyuiopasdfghjklzxcvbnm";
        SecureRandom rand = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        int codeLength = 12;

        for(int i = 0; i < codeLength; i++) {
            sb.append(validChars.charAt(rand.nextInt(validChars.length() - 1)));
        }
        return sb.toString();
    }

    public static HashMap<String, String> parseUser(User userData){
        HashMap<String, String> userMap = new HashMap<String, String>();
        userMap.put("userId", userData.getUserId());
        userMap.put("firstName", userData.getFirstName());
        userMap.put("lastName", userData.getLastName());
        userMap.put("email", userData.getEmail());
        userMap.put("accessLevel", userData.getAccessLv()+"");

        System.out.println(userMap);
        return userMap;
    }

}
