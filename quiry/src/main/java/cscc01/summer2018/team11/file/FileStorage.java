package cscc01.summer2018.team11.file;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.database.UserDAO;
import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.user.User;


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
