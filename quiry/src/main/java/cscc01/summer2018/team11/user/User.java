package cscc01.summer2018.team11.user;

import java.io.File;

import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileService;

/**
 * TODO: REVISE
 */
public abstract class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int accessLv;
    private String userId;
    private String verification;

    // access level:
    // admin = 3
    // instructor = 2
    // student = 1
    // guest = 0
    public User(String userId, String email, String password,
            String firstName, String lastName, int accessLv, String verification) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessLv = accessLv;
        this.verification = verification;
    }


	public String getVerification() {
		return verification;
	}


	public void setVerification(String verification) {
		this.verification = verification;
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

    public File getFile(int fileId) {
        return new File( getFileInfo(fileId).getPath() );
    }

    public FileInfo getFileInfo(int fileId) {
        return FileService.getFileInfo(fileId);
    }
}
