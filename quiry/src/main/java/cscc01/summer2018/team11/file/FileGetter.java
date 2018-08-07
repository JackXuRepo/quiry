package cscc01.summer2018.team11.file;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.FileSystemUtils;

public class FileGetter {

	// file storage root
	public static String root = "files";

	public static File getFile(String fileId) throws FileNotFoundException {
		String folder = root + "/" + fileId;
		File[] file = new File(folder).listFiles();

		if (file.length > 0) {
			String path = folder + "/" + file[0].getName();
			return new File(path);
		} else {
			throw new FileNotFoundException();
		}
	}

	public static File createFile(String fileId, String fileName) {
		String newFolderPath = root + "/" + fileId;
		new File(newFolderPath).mkdirs();
		return new File(newFolderPath + "/" + fileName);
	}

	public static boolean deleteFile(int fileId) {
		return deleteFile(Integer.toString(fileId));
	}

	public static boolean deleteFile(String fileId) {
		File file = new File(root + "/" + fileId);
		return FileSystemUtils.deleteRecursively(file);
	}

}
