package cscc01.summer2018.team11.lucene;

public class SortBy {

	public static final int DEFAULT   = 0;
	public static final int NEWEST    = 1;
	public static final int OLDEST    = 2;
	public static final int AUTHOR_AZ = 3;
	public static final int AUTHOR_ZA = 4;
	public static final int COURSE    = 5;

	public static String toString(int x) {
		switch (x) {
		case 0:
			return "default";
		case 1:
			return "newest";
		case 2:
			return "oldest";
		case 3:
			return "author_az";
		case 4:
			return "author_za";
		case 5:
			return "course";
		default:
			return null;
		}
	}

}
