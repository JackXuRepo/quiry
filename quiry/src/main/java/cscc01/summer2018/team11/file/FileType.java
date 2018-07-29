package cscc01.summer2018.team11.file;

public class FileType {

	public static final int NONE = -1;
	public static final int PDF  = 0;
	public static final int TEXT = 1;
	public static final int HTML = 2;

	public static String toString(int x) {
		switch (x) {
		case 0:
			return "pdf";
		case 1:
			return "txt";
		case 2:
			return "html";
		default:
			return null;
		}
	}

	public static int toFileType(String x) {
		switch (x) {
		case "pdf":
			return FileType.PDF;
		case "txt":
			return FileType.TEXT;
		case "html":
		case "htm" :
			return FileType.HTML;
		default:
			return FileType.NONE;
		}
	}

}
