package cscc01.summer2018.team11.user;


import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


public class Student extends User {
	private List<String> classes = new ArrayList<>();
	private List<String> file_id = new ArrayList<>();

	public Student(String first_name, String last_name, String email_address, String password, Image photo) {
		super(first_name, last_name, email_address, password, 1, photo);
		// Access Level set to 1
	}
	
	public List<String> getClasses() {
		return classes;
	}
	
	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
	
	public void setClasses(String classes) {
		this.classes.add(classes);
	}
	
	public List<String> getFile_id() {
		return file_id;
	}
	
	public void setFile_id(List<String> id) {
		this.file_id = id;
	}
	
	public void setFile_id(String id) {
		this.file_id.add(id);
	}
	
	public void deleteFile(String id) {
		this.file_id.remove(id);
	}
}
