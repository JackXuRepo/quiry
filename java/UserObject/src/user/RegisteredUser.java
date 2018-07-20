package cscc01.summer2018.team11.user;

import java.util.HashSet;

import cscc01.summer2018.team11.file.FileInfo2;
import cscc01.summer2018.team11.file.FileStorage;

public abstract class RegisteredUser extends User {

	private HashSet<Integer> userFiles = new HashSet<Integer>();
	private HashSet<String> courses = new HashSet<String>();

	public RegisteredUser(String userId, String email, String password,
	        String firstName, String lastName, int accessLv) {
		super(userId, email, password, firstName, lastName, accessLv);
		this.userFiles.clear();
		this.courses.clear();
	}

	public boolean addFile(String title, String description, int contentType, int accessLv,
			String course, boolean courseRestricted, String absPath) {
		FileInfo2 temp = new FileInfo2(this.getUserId(), title, description, contentType, accessLv, absPath,
				course, courseRestricted);
		return FileStorage.addFile(temp);
	}

	public boolean deleteFile(int fileId) {
		return FileStorage.deleteFile(fileId);
	}

	public boolean addFileId(int fileId) {
		// check if fileId is 5 digit number;
		if (fileId > 99999 || fileId < 9999) {
			return false;
		}
		if (this.userFiles.contains(fileId)) {
			return false;
		}
		this.userFiles.add(fileId);
		return true;
	}

	public HashSet<Integer> getFileIds() {
		return userFiles;
	}

	public boolean addCourse(String course) {
		// course code must match [A-Z][A-Z][A-Z][A-D or 1-4][0-9][0-9] eg.CSCC01 or CSC301
		if (!course.matches("^[A-Z]{3}[A-D1-4][0-9]{2}")) {
			return false;
		}
		if (this.courses.contains(course)) {
			return false;
		}
		this.courses.add(course);
		return true;
	}

	public boolean deleteCourse(String course) {
		if (!this.courses.contains(course)) {
			return false;
		}
		this.courses.remove(course);
		return true;
	}

	public HashSet<String> getCourses() {
		return courses;
	}
}