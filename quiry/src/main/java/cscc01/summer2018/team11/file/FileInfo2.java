package cscc01.summer2018.team11.file;


import java.util.Date;
import java.util.Random;

import cscc01.summer2018.team11.user.AccessLevel;


public class FileInfo2 {

    private static Random rand = new Random();

    private int fileId;
    private String userId;
    private FileType fileType;
    private ContentType contentType;
    private AccessLevel accessLv;
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
        /* TODO: replace with file system lookup */
        } while ( FileStorage.existFile(n) );
        return n;
    }

    public FileInfo2(String userId, String title, String description,
            ContentType contentType, AccessLevel accessLv, String absPath, String course,
            boolean courseRestricted, FileType fileType, long uploadMs)
    {
        this.fileId = generateId();
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.contentType = contentType;
        this.accessLv = accessLv;
        this.path = absPath;
        this.course = course;
        this.courseRestricted = courseRestricted;
        this.fileType = fileType;
        this.uploadMs = uploadMs;
    }

    public FileInfo2(String userId, String title, String description,
            ContentType contentType, AccessLevel accessLv, String absPath, String course,
            boolean courseRestricted, FileType fileType)
    {
        this(userId, title, description, contentType, accessLv, absPath,
                course, courseRestricted, fileType, System.currentTimeMillis());
    }

    public FileInfo2(String userId, String title, String description,
            ContentType contentType, AccessLevel accessLv, String absPath,
            String course, boolean courseRestricted) {
        /* get file type based on file path */
        this(userId, title, description, contentType, accessLv, absPath,
                course, courseRestricted, Parser.getFileType(absPath));
    }

    public FileInfo2(String userId, String title, String description,
            ContentType contentType, AccessLevel accessLv, String absPath) {
        /* no course set */
        this(userId, title, description, contentType, accessLv, absPath, null, false);
    }

    public String getContent() {
        return getPath(); // TODO: Parser.getContent(path);
    }

    public String getAuthor() {
        return userId;
    }

    public void setAuthor(String userId) {
        this.userId = userId;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public AccessLevel getAccessLv() {
        return accessLv;
    }

    public void setAccessLv(AccessLevel accessLv) {
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

    public Date getUploadDate() {
        return new Date(uploadMs);
    }

}
