package cscc01.summer2018.team11.user;

import java.io.File;

public abstract class User {
	
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int accessLv;
    private String userId;
    
    // access level:
    // admin = 3
    // instructor = 2
    // student = 1
    // guest = 0
    public User(String userId, String email, String password, String firstName, String lastName, int accessLv) {
    	this.userId = userId;
    	this.email = email;
    	this.password = password;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.accessLv = accessLv;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccessLv() {
		return accessLv;
	}

	public void setAccessLv(int accessLv) {
		this.accessLv = accessLv;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	public boolean addFile(FileInfo f) {
		return true;
	}
	
	public File getFile(String fileId) {
		return null;
	}
	
	public FileInfo getFileInfo(String fileId) {
		return null;
	}
	
	public boolean deleteFile(String fileId) {
		return true;
	}
}
