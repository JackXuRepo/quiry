package cscc01.summer2018.team11.user;


import java.sql.SQLException;
import java.util.Set;

import cscc01.summer2018.team11.database.UserDAO;


public class UserStorage {

    private static UserDAO userDAO;
    private static Set<String> allUsers;

    public static void initialize() {
        // read all userId from database and populate allUsers
        try {
            userDAO = new UserDAO();
            allUsers = userDAO.getAllUserIds();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static User getUser(String userId) {
        User userInfo = null;
        try {
            userInfo = userDAO.getUserByUserId(userId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            userDAO.updateUser(user);
        } catch (SQLException e) {
            return false;
        }

        return allUsers.add(user.getUserId());
    }

    public static boolean deleteUser(String userId) {
        try {
            userDAO.deleteUser(userId);
        } catch (SQLException e) {
            return false;
        }

        return allUsers.remove(userId);
    }

    public static boolean existUser(String userId) {
        return allUsers.contains(userId);
    }

}
