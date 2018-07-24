package cscc01.summer2018.team11.file;


import java.security.SecureRandom;
import java.util.Date;

import cscc01.summer2018.team11.user.AccessLevel;


public class FileInfo4 {

    private static final SecureRandom rand = new SecureRandom();

    private int fileId;
    private String userId;
    private int fileType;
    private int contentType;
    private int accessLevel;
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

    private FileInfo4(Builder builder) {
        this.fileId = builder.fileId == 0 ? generateId() : builder.fileId;
        this.userId = builder.userId;
        this.title = builder.title;
        this.description = builder.description;
        this.contentType = builder.contentType;
        this.accessLevel = builder.accessLevel;
        this.path = builder.path;
        this.course = builder.course;
        this.courseRestricted = builder.courseRestricted;
        this.fileType = builder.fileType;
        this.uploadMs = builder.uploadMs == 0 ?
                System.currentTimeMillis() : builder.uploadMs;
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

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
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

    public static class Builder {

        private int fileId = 0;
        private String userId = "";
        private int fileType = FileType.TEXT;
        private int contentType = ContentType.NOTES;
        private int accessLevel = AccessLevel.GUEST;
        private String title = "";
        private String description = "";
        private String course = "";
        private boolean courseRestricted = false;
        private String path = "";
        private long uploadMs = 0;

        public FileInfo4 build() {
            return new FileInfo4(this);
        }

        public Builder fileId(int fileId) {
            this.fileId = fileId;
            return this;
        }

        public Builder fileId(String fileId) {
            this.fileId = Integer.parseInt(fileId);
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder fileType(int fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder contentType(int contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder accessLevel(int accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder course(String course) {
            this.course = course.toLowerCase();
            return this;
        }

        public Builder courseRestricted(boolean courseRestricted) {
            this.courseRestricted = courseRestricted;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder uploadMs(long uploadMs) {
            this.uploadMs = uploadMs;
            return this;
        }

    }

}
