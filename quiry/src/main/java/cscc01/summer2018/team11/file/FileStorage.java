package cscc01.summer2018.team11.file;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.lucene.Index;


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

}
