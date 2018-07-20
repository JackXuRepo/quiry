package cscc01.summer2018.team11.file;


import java.sql.SQLException;
import java.util.Set;

import cscc01.summer2018.team11.database.FileDAO;


public class FileStorage {

    private static FileDAO fileDAO;
    private static Set<Integer> allFiles;

    public static void initialize() {
        /* TODO: read all fileId from disk and populate allFiles set */
        try {
            fileDAO = new FileDAO();
            allFiles = fileDAO.getAllFileIds();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean addFile(FileInfo2 fileInfo) {
        int fileId = fileInfo.getId();
        if ( existFile(fileId) ) {
            return false;
        }

        try {
            fileDAO.addFile(fileInfo);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        allFiles.add(fileId);
        return true;
    }

    public static boolean existFile(int fileId) {
        return allFiles.contains(fileId);
    }

}
