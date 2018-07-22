package cscc01.summer2018.team11.file;

public class ContentType {

	public static final int NONE    = -1;
	public static final int EXAM    = 0;
	public static final int NOTES   = 1;
	public static final int JOURNAL = 2;

	public static String toString(int x) {
		switch (x) {
		case 0:
			return "exam";
		case 1:
			return "notes";
		case 2:
			return "journal";
		default:
			return null;
		}
	}

}
