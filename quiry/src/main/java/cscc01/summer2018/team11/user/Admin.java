package cscc01.summer2018.team11.user;


import java.util.HashSet;

import cscc01.summer2018.team11.file.FileInfo2;


public class Admin extends User {

	public Admin(String userId, String email, String password,
	        String firstName, String lastName) {
		super(userId, email, password, firstName, lastName, AccessLevel.ADMIN);
	}
	
	public User getUser(String eid) {
		return null;
	}
	
	public HashSet<String> getFiles(String userId) {
		return null;
	}
	
	public boolean addFile(FileInfo2 f) {
		return true;
	}
	
	public boolean deleteFile(String fileId) {
		return true;
	}
	
	public boolean setAccessLv(String userId, int lv) {
		return true;
	}
	
	public boolean setEmail(String userId, String email) {
		return true;
	}
	
	public boolean setPassword(String userId, String password) {
		return true;
	}
	
	public boolean setFirstName(String userId, String fn) {
		return true;
	}
	
	public boolean setLastName(String userId, String ln) {
		return true;
	}
}
