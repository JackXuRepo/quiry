package cscc01.summer2018.team11.user;

public class Instructor extends RegisteredUser {

	public Instructor(String userId, String email, String password,
	        String firstName, String lastName) {
		super(userId, email, password, firstName, lastName, AccessLevel.INSTRUCTOR);
	}

}
