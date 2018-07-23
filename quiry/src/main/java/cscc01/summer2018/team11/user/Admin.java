package cscc01.summer2018.team11.user;

public class Admin extends User {

	public Admin(String userId, String email, String password,
			String firstName, String lastName) {
		super(userId, email, password, firstName, lastName, AccessLevel.ADMIN);
	}

}
