package cscc01.summer2018.team11.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileGetter {
	
	//Replace with our file storage root
	public static String root = "/cmshome/make2/test/files/";
	
	public static void main(String args[]) {
		if (args.length == 1) {
			System.out.println(getFile(args[0]));
		} else {
			//System.out.println("Please enter one file ID.");
			//System.out.println(pseudoUpload("/cmshome/make2/test/uploadme.txt"));
		}
	}
	
	public static String getFile (String id) {
		try {
			String path = root + id;
			File folder = new File(path);
			File[] file = folder.listFiles();
			String fullPath = path + "/" + file[0].getName();
			return fullPath;
		} catch (Exception NullPointerException){
			return "File ID is invalid.";
		}
	}
	
	public static String pseudoUpload (String path) {
		//Get name of file
		String fileName = path.substring(path.lastIndexOf("/"));
		//Replace with random file ID generator
		File storage = new File(root);
		File[] files = storage.listFiles();
		int latestFileID = 1;
		for (int i=0; i < files.length; i++) {
			latestFileID++;
		}
		//End replace 
		String newFolderPath = root + String.valueOf(latestFileID); //Replace String.valueOf(latestFileID) with randomly generated ID.
		new File(newFolderPath).mkdirs();
		Path sourcePath = Paths.get(path);
		Path destinationPath = Paths.get(newFolderPath + fileName);
		try {
			Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
			return String.valueOf(latestFileID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Upload failed.";
		}

	}
}
