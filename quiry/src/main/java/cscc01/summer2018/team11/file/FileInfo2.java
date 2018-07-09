package cscc01.summer2018.team11.file;


import java.util.Random;


public class FileInfo2 {

    private String id;
    private String author_email;
    private String title;
    private String description;
    private String course;
    private int access_lv;
    private String file_type;
    private String content;

    public static String generateID() {
        int id = new Random().nextInt(8999) + 1000;
        return Integer.toString(id);
    }

    public FileInfo2(String author_email, String title, String description,
                    String course, int access_lv, String file_type, String content) {
        this.id = generateID();
        this.author_email = author_email;
        this.title = title;
        this.description = description;
        this.course = course;
        this.access_lv = access_lv;
        this.file_type = file_type;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getAuthorEmail() {
        return author_email;
    }

    public void setAuthorEmail(String email) {
        this.author_email = email;
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

    public int getAccessLevel() {
        return access_lv;
    }

    public void setAccessLevel(int access_lv) {
        this.access_lv = access_lv;
    }

    public String getFileType() {
        return file_type;
    }

    public void setFileType(String file_type) {
        this.file_type = file_type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "FileInfo [id=" + id + ", author_email=" + author_email +
            ", title=" + title + ", description=" + description + ", course=" +
            course + ", access_lv=" + access_lv + ", file_type=" + file_type + "]";
    }

}
