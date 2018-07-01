package user_object;

import file_storage.FileInfo;
import file_storage.FileStorage;
import javafx.scene.image.Image;

public class Admin extends User {

	public Admin(String first_name, String last_name, String email_address, String password, Image photo) {
		super(first_name, last_name, email_address, password, 3, photo);
		// Access Level set to 3
	}
	
	/**
	 * @param email_address: the User's address
	 * @Return the user's basic information into String
	 * 
	 * For example:
	 * email: kin.he@mail.utoronto.ca
	 * name: WeiJian He
	 * Status: Student (access level: 1)
	 * Access Time:2018/06/26 00:15(Last access)  2018/06/25 21:11  2018/06/23 22:15 ... 2018/06/15 13:28(First access)
	 * Posted File: id(51235)
	 */
	public static String getUserInfo(String email_address) {
		return UserStorage.getUserInfo(email_address);
	}
	
	/**
	 * @param email_address: the User's address
	 * @return the profile picture of that user
	 */
	public Image getUserPhoto(String email_address) {
		return null;
	}
	
	/**
	 * @param email_address: the User's address
	 * @param everything: True iff include the upload file, otherwise False
	 * @return True if deleted successfully, False if the account does not exist or account can not be deleted.
	 * 
	 * Delete the user account 
	 * If everything is set to be True, then all he/she's updated file will also be deleted
	 */
	public boolean deleteUser(String email_address, boolean everything) {
		// boolean everything is not working right now
		return UserStorage.deleteUser(email_address);
	}
	
	/**
	 * @param id
	 * @return the FileInfo as object
	 */
	public FileInfo getFileInfo(String id) {
		return FileStorage.getFileInfo(id);
	}
	
	/**
	 * @param id
	 * @return True if the file deleted successfully, False if the file does not exist
	 * 
	 * Delete the user's updated file by the file id provided
	 */
	public boolean deleteFile(String id) {
		return FileStorage.deleteFile(id);
	}
	
	/**
	 * @param author_email
	 * @param title
	 * @param description
	 * @param course
	 * @param file_type
	 * @return True if the link page deleted successfully, False if the link page does not exist
	 * 
	 * Delete the link page(all the file).
	 * EX: user upload A,B,C files in one time, and use this method, can deleted them all at once.
	 */
	public boolean deletePage(String author_email, String title, String description, String course, String file_type) {
		return true;
	}
	
	
}
