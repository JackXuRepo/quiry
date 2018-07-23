package cscc01.summer2018.team11.file;


import java.security.SecureRandom;
import java.util.Date;


public class FileInfo {

    private static final SecureRandom rand = new SecureRandom();

    private int fileId;
    private String userId;
    private int fileType;
    private int contentType;
    private int accessLv;
    private String title;
    private String description;
    private String course;
    private boolean courseRestricted;
    private String path;
    private long uploadMs;

    public static int generateId() {
        int n;
        do {
            n = rand.nextInt(8999) + 1000;
        } while ( FileStorage.existFile(n) );
        return n;
    }

    public FileInfo(String userId, String title, String description,
            int contentType, int accessLv, String absPath, String course,
            boolean courseRestricted, int fileType, long uploadMs, int fileId)
    {
        this.fileId = fileId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.contentType = contentType;
        this.accessLv = accessLv;
        this.path = absPath;
        this.course = course.toLowerCase();
        this.courseRestricted = courseRestricted;
        this.fileType = fileType;
        this.uploadMs = uploadMs;
    }

    public FileInfo(String userId, String title, String description,
            int contentType, int accessLv, String absPath, String course,
            boolean courseRestricted, int fileType, long uploadMs)
    {
        this(userId, title, description, contentType, accessLv, absPath,
                course, courseRestricted, fileType, uploadMs, generateId());
    }

    public FileInfo(String userId, String title, String description,
            int contentType, int accessLv, String absPath, String course,
            boolean courseRestricted, int fileType)
    {
        this(userId, title, description, contentType, accessLv, absPath,
                course, courseRestricted, fileType, System.currentTimeMillis());
    }

    public FileInfo(String userId, String title, String description,
            int contentType, int accessLv, String absPath,
            String course, boolean courseRestricted) {
        /* get file type based on file path */
        this(userId, title, description, contentType, accessLv, absPath,
                course, courseRestricted, Parser.getFileType(absPath));
    }

    public FileInfo(String userId, String title, String description,
            int contentType, int accessLv, String absPath) {
        /* no course set */
        this(userId, title, description, contentType, accessLv, absPath, null, false);
    }

    public String getContent() {
        return Parser.getContent(path);
    }

    public String getAuthor() {
        return userId;
    }

    public void setAuthor(String userId) {
        this.userId = userId;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getAccessLv() {
        return accessLv;
    }

    public void setAccessLv(int accessLv) {
        this.accessLv = accessLv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isCourseRestricted() {
        return courseRestricted;
    }

    public void setCourseRestricted(boolean courseRestricted) {
        this.courseRestricted = courseRestricted;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return fileId;
    }

    public String getFileId() {
        return Integer.toString(fileId);
    }

    public long getUploadMs() {
        return uploadMs;
    }

    public Date getUploadDate() {
        return new Date(uploadMs);
    }

}
