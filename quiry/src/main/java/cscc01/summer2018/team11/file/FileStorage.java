package cscc01.summer2018.team11.file;


import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.lucene.Index;


@Service
public class FileStorage {

    public static FileInfo getFileInfo(int fileId) {
        FileInfo fileInfo = null;
        try {
            FileDAO fileDb = new FileDAO();
            fileInfo = fileDb.getFileByFileId(fileId);

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
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
            FileDAO fileDb = new FileDAO();
            fileDb.updateFile(fileInfo);

            Index.removeFile(fileInfo.getId());
            Index.indexFile(fileInfo);

        } catch (SQLException | IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteFile(int fileId) {
        try {
            FileDAO fileDb = new FileDAO();
            fileDb.deleteFile(fileId);
            Index.removeFile(fileId);

        } catch (SQLException | IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteFile(String fileId) {
        int id = Integer.valueOf(fileId);
        return deleteFile(id);
    }

    public static boolean existFile(int fileId) {
        boolean exist = false;
        try {
            FileDAO fileDb = new FileDAO();
            exist = !(fileDb.getFileByFileId(fileId) == null);

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return exist;
    }

}
