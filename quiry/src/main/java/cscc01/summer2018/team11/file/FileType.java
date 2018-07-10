package cscc01.summer2018.team11.file;

public enum FileType {
    PDF ("pdf"),
    TEXT ("txt"),
    HTML ("html");

    private final String name;

    private FileType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
