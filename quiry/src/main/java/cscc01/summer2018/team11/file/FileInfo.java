package cscc01.summer2018.team11.file;

import java.io.File;

public class FileInfo {

	private String id;
	private String author_email;
	private String title;
	private String description;
	private String course;
	private int access_lv;
	private String file_type;
	private File f;

	public FileInfo(String id, String author_email, String title,
			String description, String course, int access_lv, String file_type, File f) {
		this.id = id;
		this.author_email = author_email;
		this.title = title;
		this.description = description;
		this.course = course;
		this.access_lv = access_lv;
		this.file_type = file_type;
		this.f = f;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor_email() {
		return author_email;
	}

	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
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

	public int getAccess_lv() {
		return access_lv;
	}

	public void setAccess_lv(int access_lv) {
		this.access_lv = access_lv;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	public String getContent() {
		String fileName = f.getAbsolutePath();
		return Parser.getContent(fileName);
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", author_email=" + author_email + ", title=" + title + ", description="
				+ description + ", course=" + course + ", access_lv=" + access_lv + ", file_type=" + file_type + ", f="
				+ f + "]";
	}

}
