package cscc01.summer2018.team11;


import java.security.SecureRandom;
import java.sql.SQLException;

import cscc01.summer2018.team11.database.UserDAO;
import cscc01.summer2018.team11.user.Instructor;
import cscc01.summer2018.team11.user.Student;


/**
 * Test UserDAO
 */
public class UserDAOTest {

	private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final SecureRandom rand = new SecureRandom();

	public static String generateString(int len){
		StringBuilder sb = new StringBuilder(len);

		for (int i = 0; i < len; i++) {
			int n = rand.nextInt( CHARSET.length());
			sb.append( CHARSET.charAt(n) );
		}
		return sb.toString();
	}

	public static void main(String[] args) throws SQLException {

		String firstName = "Jack";
		String lastName = "Xu";
		String email = "haosen.xu@mail.utoronto.ca";
		String password = "password";

		Student a = new Student(generateString(8), email, password, firstName, lastName);
		Instructor b = new Instructor(generateString(8), email, password, firstName, lastName);

		UserDAO userDatabase = new UserDAO();
		userDatabase.updateUser(a);
		userDatabase.updateUser(b);

		for (String id : userDatabase.getAllUserIds()) {
			System.out.println(id + " : " + userDatabase.getUserByUserId(id));
		}
	}

}
