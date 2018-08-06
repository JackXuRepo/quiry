package cscc01.summer2018.team11.user;

import java.util.HashSet;

public abstract class RegisteredUser extends User {

	private HashSet<Integer> userFiles = new HashSet<Integer>();
	private HashSet<String> courses = new HashSet<String>();

	public RegisteredUser(String userId, String email, String password,
	        String firstName, String lastName, int accessLv, String verification) {
		super(userId, email, password, firstName, lastName, accessLv, verification);
		this.userFiles = null;
		this.courses = null;
	}

}