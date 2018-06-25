package file_storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
	public static List<FileInfo> storage = new ArrayList<>();
	
	public static String addFile(String author_email, File f){
		String id = FileIDSystem.generate();
		storage.add(new FileInfo(id, author_email, "","","",0,"",f));
		return id;
	}

	public static void deleteFile(String id){
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).getId() == id) {
				FileIDSystem.deleteID(id);
				storage.remove(i);
			}
		}
	}
	
	public static FileInfo getFileInfo(String id){
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).getId() == id) {
				return storage.get(i);
			}
		}
		return null;
	}
	
	public void setFileInfo(String id, String author_email, String title,
			String description, String course, int access_lv, String file_type) {
		FileInfo temp = getFileInfo(id);
		temp.setAuthor_email(author_email);
		temp.setTitle(title);
		temp.setDescription(description);
		temp.setCourse(course);
		temp.setAccess_lv(access_lv);
		temp.setFile_type(file_type);
	}
	
	public void changeFile(String id, File f) {
		FileInfo temp = getFileInfo(id);
		temp.setF(f);
	}
}
