package user_object;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import file_storage.FileStorage;
import javafx.scene.image.Image;

public class UserStorage {
    
    public static List<Admin> adminStorage = new ArrayList<>();
    public static List<Instructor> instructorStorage = new ArrayList<>();
    public static List<Student> studentStorage = new ArrayList<>();
    
    public static boolean userExist(String email_address) {
    	for (int i = 0; i < adminStorage.size(); i++) {
			if (adminStorage.get(i).getEmail_address() == email_address) {
				return true;
			}
		}
    	for (int i = 0; i < instructorStorage.size(); i++) {
			if (instructorStorage.get(i).getEmail_address() == email_address) {
				return true;
			}
		}
		for (int i = 0; i < studentStorage.size(); i++) {
			if (studentStorage.get(i).getEmail_address() == email_address) {
				return true;
			}
		}
		return false;
    }

    public static boolean addUser(String first_name, String last_name, String email_address, String password, 
    		int access_lv, Image photo) {
    	if(userExist(email_address) == true) {
    		return false;
    	}
    	if (access_lv == 3) {
    		adminStorage.add(new Admin(first_name, last_name, email_address, password, photo));
    	}
    	else if (access_lv == 2) {
    		instructorStorage.add(new Instructor(first_name, last_name, email_address, password, photo));
    	}
    	else if (access_lv == 1) {
    		studentStorage.add(new Student(first_name, last_name, email_address, password, photo));
    	}
    	else {
    		return false;
    	}
    	return true;
    }
    
    public static int getAccessLevel(String email_address) {
    	for (int i = 0; i < adminStorage.size(); i++) {
			if (adminStorage.get(i).getEmail_address() == email_address) {
				return 3;
			}
		}
    	for (int i = 0; i < instructorStorage.size(); i++) {
			if (instructorStorage.get(i).getEmail_address() == email_address) {
				return 2;
			}
		}
    	for (int i = 0; i < studentStorage.size(); i++) {
			if (studentStorage.get(i).getEmail_address() == email_address) {
				return 1;
			}
		}
    	return 0;
    }
    
    public static Admin findAdminUser(String email_address) {
    	Admin temp = null;
    	for (int i = 0; i < adminStorage.size(); i++) {
    		if (adminStorage.get(i).getEmail_address() == email_address) {
    			temp = adminStorage.get(i);
    		}
    	}
    	return temp;
    }
    
    public static Instructor findInstructorUser(String email_address) {
    	Instructor temp = null;
    	for (int i = 0; i < instructorStorage.size(); i++) {
    		if (instructorStorage.get(i).getEmail_address() == email_address) {
    			temp = instructorStorage.get(i);
    		}
    	}
    	return temp;
    }
    
    public static Student findStudentUser(String email_address) {
    	Student temp = null;
    	for (int i = 0; i < studentStorage.size(); i++) {
    		if (studentStorage.get(i).getEmail_address() == email_address) {
    			temp = studentStorage.get(i);
    		}
    	}
    	return temp;
    }
    
    public static void editUserClasses(String email_address, String classes) {
    	int lv = getAccessLevel(email_address);
    	if (lv == 2) {
    		findInstructorUser(email_address).setClasses(classes);
    	}
    	else if (lv == 1) {
    		findStudentUser(email_address).setClasses(classes);
    	}
    	else {
    		System.out.println("Email is wrong!");
    	}
    }
    
    public static String getListOfClasses(String email_address) {
    	int lv = getAccessLevel(email_address);
    	String result = "";
    	if (lv == 2) {
    		for (int i = 0; i < findInstructorUser(email_address).getClasses().size(); i++) {
    			result += findInstructorUser(email_address).getClasses().get(i) + ", ";
    		}
    	}
    	else if (lv == 1) {
    		findStudentUser(email_address).getClasses();
    		for (int i = 0; i < findStudentUser(email_address).getClasses().size(); i++) {
    			result += findStudentUser(email_address).getClasses().get(i) + ", ";
    		}
    	}
    	else {
    		return "empty";
    	}
    	return result.replaceAll(",([^,]*)$", "$1");
    }
    
    public static boolean deleteUser(String email_address) {
    	int lv = getAccessLevel(email_address);
    	if (lv == 3) {
    		adminStorage.remove(findAdminUser(email_address));
    		return true;
    	}
    	else if (lv == 2) {
    		instructorStorage.remove(findInstructorUser(email_address));
    		return true;
    	}
    	else if (lv == 1) {
    		studentStorage.remove(findStudentUser(email_address));
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public static String getUserInfo(String email_address) {
    	String result;
    	String classes = "";
    	String files = "";
    	int lv = getAccessLevel(email_address);
    	if (lv == 3) {
    		result = "Name: " + findAdminUser(email_address).getFirst_name() + " " + findAdminUser(email_address).getLast_name() + 
    				"" + "\nEmail: " + findAdminUser(email_address).getEmail_address() + "\nStatus: Admin";
			return result;
    	}
    	else if (lv == 2) {
			classes = getListOfClasses(email_address);
			files = FileStorage.getListOfFile(email_address);
			result = "Name: " + findInstructorUser(email_address).getFirst_name() + " " + findInstructorUser(email_address).getLast_name()
					+ "\nEmail: " + findInstructorUser(email_address).getEmail_address() + "\nStatus: Instructor\nClass Teaching: " 
					+ classes + "\nUploaded File(s): " + files;
			return result;
    	}
    	else if (lv == 1) {
			classes = getListOfClasses(email_address);
			files = FileStorage.getListOfFile(email_address);
			result = "Name: " + findStudentUser(email_address).getFirst_name() + " " + findStudentUser(email_address).getLast_name()
					+ "\nEmail: " + findStudentUser(email_address).getEmail_address() + "\nStatus: Student\nClass Studying: " 
					+ classes + "\nUploaded File(s): " + files;
			return result;
    	}
    	else {
    		return "The account does not exist.";
    	}
    }
    
    public static void uploadAFile(String email_address, File f, int access_lv) {
    	int lv = getAccessLevel(email_address);
    	if (lv != 2 && lv != 3) {
    		System.out.println("Upload Fail! Email is wrong.");
    		return;
    	}
    	if (access_lv > lv) {
    		System.out.println("Upload Fail! You can't upload file that permission is higher than your current one.");
    	}
    	String id = FileStorage.addFile(email_address, f, access_lv);
    	if (lv == 2) {
    		findInstructorUser(email_address).setFile_id(id);
    	}
    	else {
    		findStudentUser(email_address).setFile_id(id);
    	}
    	System.out.println("Upload Success!");
    }
    
    public static void readAFile(String email_address, String id) throws Exception {
    	int lv = getAccessLevel(email_address);
    	int file_lv = FileStorage.getFileInfo(id).getAccess_lv();
    	if (FileStorage.getFileInfo(id).getAuthor_email() != email_address && lv < file_lv) {
    		System.out.println("You don't have the permission to access this file.");
    		return;
    	}
		File tempFile = FileStorage.getFileInfo(id).getF();
		FileReader fr = new FileReader(tempFile.getPath());
		int i;
	    while ((i=fr.read()) != -1) {
	      System.out.print((char) i);
	    }
	}
    
    public static void deleteAFile(String email_address, String id) {
		if (!FileStorage.fileExist(id)) {
			System.out.println("File does not exist.");
			return;
		}
    	int lv = getAccessLevel(email_address);
    	if (FileStorage.getFileInfo(id).getAuthor_email() == email_address || lv == 3) {
    		FileStorage.deleteFile(id);
    		if (lv == 2) {
        		findInstructorUser(email_address).deleteFile(id);
        	}
        	else if (lv == 1) {
        		findStudentUser(email_address).deleteFile(id);
        	}
    	}
    }
}
