package user_object;

import javafx.scene.image.Image;

public class User {
	
    private String first_name;
    private String last_name;
    private String email_address;
    private String password;
    private int access_lv;
    private Image photo;
    
    // access level:
    // admin = 3
    // instructor = 2
    // student = 1
    // guest = 0
    public User(String first_name, String last_name, String email_address, String password, int access_lv, Image photo) {
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.email_address = email_address;
    	this.password = password;
    	this.access_lv = access_lv;
    	this.photo = photo;
    }

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail_address() {
		return email_address;
	}

	//email can not be set again, once he/she registered, unless delete the user
	//public void setEmail_address(String email_address) {
	//	this.email_address = email_address;
	//}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccess_lv() {
		return access_lv;
	}

	public void setAccess_lv(int access_lv) {
		this.access_lv = access_lv;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}
}
