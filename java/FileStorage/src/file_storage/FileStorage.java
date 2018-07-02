package file_storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
	public static List<FileInfo> storage = new ArrayList<>();
	
	public static String addFile(String author_email, File f){
		String id = FileIDSystem.generate();
		storage.add(new FileInfo(id, author_email, f.getName(),"","",0,"",f));
		return id;
	}

	public static boolean deleteFile(String id){
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).getId() == id) {
				FileIDSystem.deleteID(id);
				storage.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public static FileInfo getFileInfo(String id){
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).getId() == id) {
				return storage.get(i);
			}
		}
		return null;
	}
	
	public static List<String> getFileID(String author_email) {
		List<String> temp = new ArrayList<>();
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).getAuthor_email() == author_email) {
				temp.add(storage.get(i).getId());
			}
		}
		return temp;
	}
	
	public static String getFileName(String id) {
		String result = "";
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).getId() == id) {
				result = storage.get(i).getTitle();
			}
		}
		return result;
	}
	
	public static String getListOfFile(String author_email) {
		List<String> temp = getFileID(author_email);
		String result = "";
		for (int i = 0; i < temp.size(); i++) {
			result += getFileName(temp.get(i)) + "(" + temp.get(i) + ") ";
		}
		return result;
	}
}
