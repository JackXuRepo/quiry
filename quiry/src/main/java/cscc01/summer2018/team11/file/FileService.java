package cscc01.summer2018.team11.file;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.UserService;


@Service
public class FileService {

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
            FileGetter.deleteFile(fileId);

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

    public static List<FileInfo> getUserFiles(String userId) {
        List<FileInfo> results = null;
        try {
            FileDAO fileDb = new FileDAO();
            results = fileDb.getFilesByUserId(userId);

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return results;
    }

    public static HashMap<String, String> parseFileInfo(FileInfo fileInfo, String preview) {
        HashMap<String, String> fileInfoMap = new HashMap<String, String>();
        User author = UserService.getUser(fileInfo.getAuthor());

        fileInfoMap.put("fileId", fileInfo.getFileId());
        fileInfoMap.put("title", fileInfo.getTitle());
        fileInfoMap.put("fileType", fileInfo.getFileType()+"");
        fileInfoMap.put("contentType", fileInfo.getContentType()+"");
        fileInfoMap.put("author", author.getFirstName() + " " + author.getLastName());
        fileInfoMap.put("authorType", author.getAccessLv()+"");
        fileInfoMap.put("course", fileInfo.getCourse());
        fileInfoMap.put("uploadDate", fileInfo.getUploadDate().toString());
        fileInfoMap.put("description", fileInfo.getDescription());
        fileInfoMap.put("preview", preview);

        System.out.println(fileInfoMap);
        return fileInfoMap;
    }

}
