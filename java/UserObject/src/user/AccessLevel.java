package cscc01.summer2018.team11.user;

public class AccessLevel {

	public static final int GUEST    = 0;
	public static final int STUDENT   = 1;
	public static final int INSTRUCTOR = 2;
	public static final int ADMIN = 3;

	public static String toString(int x) {
		switch (x) {
		case 0:
			return "guest";
		case 1:
			return "student";
		case 2:
			return "instructor";
		case 3:
			return "admin";
		default:
			return null;
		}
	}

}
