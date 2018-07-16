package cscc01.summer2018.team11.user;

public class Student extends RegisteredUser {

	public Student(String userId, String email, String password,
	        String firstName, String lastName) {
		super(userId, email, password, firstName, lastName, AccessLevel.STUDENT);
	}

}
