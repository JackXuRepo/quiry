package cscc01.summer2018.team11.user;

import java.util.HashSet;

import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileService;

public abstract class RegisteredUser extends User {

	private HashSet<Integer> userFiles = new HashSet<Integer>();
	private HashSet<String> courses = new HashSet<String>();

	public RegisteredUser(String userId, String email, String password,
	        String firstName, String lastName, int accessLv, String verification) {
		super(userId, email, password, firstName, lastName, accessLv, verification);
		this.userFiles = null;
		this.courses = null;
	}

	public boolean addFile(String title, String description, int contentType, int accessLv,
			String course, boolean courseRestricted, String absPath) {
//		FileInfo temp = new FileInfo(this.getUserId(), title, description, contentType, accessLv, absPath,
//				course, courseRestricted);
//		return FileStorage.addFile(temp);
		return false;
	}

	public boolean deleteFile(int fileId) {
		return FileService.deleteFile(fileId);
	}

	public boolean addFileId(int fileId) {
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

	public HashSet<Integer> getUserFiles() {
		return userFiles;
	}
}