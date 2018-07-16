package cscc01.summer2018.team11.file;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class FileStorage {

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

}
