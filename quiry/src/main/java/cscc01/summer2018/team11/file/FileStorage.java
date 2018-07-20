package cscc01.summer2018.team11.file;


import java.sql.SQLException;
import java.util.Set;

import cscc01.summer2018.team11.database.FileDAO;


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

    public static FileInfo2 getFileInfo(int fileId) {
        FileInfo2 fileInfo = null;
        try {
            fileInfo = fileDAO.getFileByFileId(fileId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileInfo;
    }

    public static boolean addFile(FileInfo2 fileInfo) {
        int fileId = fileInfo.getId();
        if ( existFile(fileId) ) {
            return false;
        }

        return updateFile(fileInfo);
    }

    public static boolean updateFile(FileInfo2 fileInfo) {
        try {
            fileDAO.updateFile(fileInfo);
        } catch (SQLException e) {
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

        return allFiles.remove(fileId);
    }

    public static boolean existFile(int fileId) {
        return allFiles.contains(fileId);
    }

}
