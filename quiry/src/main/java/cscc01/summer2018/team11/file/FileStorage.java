package cscc01.summer2018.team11.file;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.database.UserDAO;
import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.user.User;


public class FileStorage {

    private static FileDAO fileDAO;
    private static Set<Integer> allFiles;

    public static void initialize() {
        // read all fileId from database and populate allFiles
        try {
            fileDAO = new FileDAO();
            allFiles = fileDAO.getAllFileIds();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static FileInfo getFileInfo(int fileId) {
        FileInfo fileInfo = null;
        try {
            fileInfo = fileDAO.getFileByFileId(fileId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileInfo;
    }

    public static FileInfo getFileInfo(String fileId) {
        int id = Integer.valueOf(fileId);
        return getFileInfo(id);
    }

    public static boolean addFile(FileInfo fileInfo) {
        int fileId = fileInfo.getId();
        if ( existFile(fileId) ) {
            return false;
        }

        return updateFile(fileInfo);
    }

    public static boolean updateFile(FileInfo fileInfo) {
        try {
            fileDAO.updateFile(fileInfo);
            Index.indexFile(fileInfo);
        } catch (SQLException | IOException ex) {
            return false;
        }

        return allFiles.add(fileInfo.getId());
    }

    public static boolean deleteFile(int fileId) {
        try {
            fileDAO.deleteFile(fileId);
        } catch (SQLException e) {
            return false;
        }

        // TODO: update index
        return allFiles.remove(fileId);
    }

    public static boolean deleteFile(String fileId) {
        int id = Integer.valueOf(fileId);
        return deleteFile(id);
    }

    public static boolean existFile(int fileId) {
        return allFiles.contains(fileId);
    }
    
	public static HashMap<String, String> parseFileInfo(FileInfo fileInfo) throws SQLException {
		HashMap<String, String> fileInfoMap = new HashMap<String, String>();
		UserDAO userDao = new UserDAO();
		User author = userDao.getUserByUserId(fileInfo.getAuthor());
		
		fileInfoMap.put("fileId", fileInfo.getFileId());
		fileInfoMap.put("title", fileInfo.getTitle());
		fileInfoMap.put("fileType", fileInfo.getFileType()+"");
		fileInfoMap.put("contentType", fileInfo.getContentType()+"");
		fileInfoMap.put("author", author.getFirstName() + " " + author.getLastName());
		fileInfoMap.put("authorType", author.getAccessLv()+"");
		fileInfoMap.put("course", fileInfo.getCourse());
		fileInfoMap.put("uploadDate", fileInfo.getUploadDate().toString());
		fileInfoMap.put("decription", fileInfo.getDescription());
		fileInfoMap.put("preview", "...is the difference between VALIDATION and ver... ...compare the following SPRINT burndown cha...");
		
		System.out.println(fileInfoMap);
		return fileInfoMap;
		
	}
	

}
