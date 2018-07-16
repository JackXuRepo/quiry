package cscc01.summer2018.team11.file;


public class FileInfo3 {
	
	private int fileId;
	private String title;
	private String description;
	private String course;
	private int accessLevel;
	private int fileType;
	private int contentType;
	private boolean courseRestricted;
	private String userId;
	private String filePath;
	
	
	public FileInfo3(int id, String userId, String title,
			String description, String course, int accessLevel, int fileType, int contentType, String filePath, boolean courseRestricted) {
		this.fileId = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.course = course;
		this.accessLevel = accessLevel;
		this.fileType = fileType;
		this.contentType = contentType;
		this.filePath = filePath;
		this.courseRestricted = courseRestricted;
	}


	public boolean isCourseRestricted() {
		return courseRestricted;
	}


	public void setCourseRestricted(boolean courseRestricted) {
		this.courseRestricted = courseRestricted;
	}


	public int getFileId() {
		return fileId;
	}


	public void setFileId(int fileId) {
		this.fileId = fileId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public int getAccessLevel() {
		return accessLevel;
	}


	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}


	public int getFileType() {
		return fileType;
	}


	public void setFileType(int fileType) {
		this.fileType = fileType;
	}


	public int getContentType() {
		return contentType;
	}


	public void setContentType(int contentType) {
		this.contentType = contentType;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}