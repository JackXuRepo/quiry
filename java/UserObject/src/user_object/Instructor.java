package user_object;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Instructor extends User {
	private List<String> classes = new ArrayList<>();
	private List<String> file_id = new ArrayList<>();

	public Instructor(String first_name, String last_name, String email_address, String password, Image photo) {
		super(first_name, last_name, email_address, password, 2, photo);
		// Access Level set to 2
	}
	
	public List<String> getClasses() {
		return classes;
	}
	
	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
	
	public void setClasses(String classes) {
		if (this.classes.contains(classes)) {
			return;
		}
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
}
