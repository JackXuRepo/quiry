package cscc01.summer2018.team11.file;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class FileStorage {

    @Deprecated
    private static List<FileInfo> storage = new ArrayList<>();

    private static Map<Integer, FileInfo2> runtimeStorage = new HashMap<>();
    private static Set<Integer> allFiles = new HashSet<>();

    public static void initialize() {
        /* TODO: read all fileId from disk and populate allFiles set */
    }

    public static boolean addFile(FileInfo2 fileInfo) {
        int fileId = fileInfo.getId();
        if ( existFile(fileId) ) {
            return false;
        }

        runtimeStorage.put(fileId, fileInfo);
        allFiles.add(fileId);
        return true;
    }

    public static boolean existFile(int fileId) {
        return allFiles.contains(fileId);
    }

    @Deprecated
    public static String generate() {
        int id = FileInfo2.generateId();
        return Integer.toString(id);
    }

    @Deprecated
    public static String addFile(String author_email, File f, int access_lv){
        String id = generate();
        storage.add(new FileInfo(id, author_email, f.getName(),"","",access_lv,"",f));
        return id;
    }

    @Deprecated
    public static boolean deleteFile(String id){
        if (!fileExist(id)) {
            return false;
        }
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                storage.remove(i);
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static FileInfo getFileInfo(String id){
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                return storage.get(i);
            }
        }
        return null;
    }

    @Deprecated
    public static List<String> getFileID(String author_email) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getAuthor_email() == author_email) {
                temp.add(storage.get(i).getId());
            }
        }
        return temp;
    }

    @Deprecated
    public static String getFileName(String id) {
        String result = "";
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                result = storage.get(i).getTitle();
            }
        }
        return result;
    }

    @Deprecated
    public static String getListOfFile(String author_email) {
        List<String> temp = getFileID(author_email);
        String result = "";
        for (int i = 0; i < temp.size(); i++) {
            result += getFileName(temp.get(i)) + "(" + temp.get(i) + ") ";
        }
        return result;
    }

    @Deprecated
    public static boolean fileExist(String id) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

}
