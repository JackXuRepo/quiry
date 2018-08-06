package cscc01.summer2018.team11.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cscc01.summer2018.team11.file.FileInfo;


public class FileDAO {

    private Connection c;
    private Statement stmt;

    public FileDAO() throws SQLException {
        this.c = Database.getConnection();
        this.stmt = c.createStatement();
    }

    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public void updateFile(FileInfo fileData) throws SQLException {
        String sql = "INSERT OR REPLACE INTO File (fileId, userId, fileType,"
                + " contentType, accesslvl, title, course, courseRestricted,"
                + " filePath, description, uploadMs)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement stmt = c.prepareStatement(sql);

        int i = 1;
        stmt.setInt(i++, fileData.getId());
        stmt.setString(i++, fileData.getAuthor());           // Need to add userId to FileInfo
        stmt.setInt(i++, fileData.getFileType());
        stmt.setInt(i++, fileData.getContentType());         // Need to add content type to FileInfo
        stmt.setInt(i++, fileData.getAccessLevel());
        stmt.setString(i++, fileData.getTitle());
        stmt.setString(i++, fileData.getCourse());
        stmt.setBoolean(i++, fileData.isCourseRestricted()); // Need to add course restricted to FileInfo
        stmt.setString(i++, fileData.getPath());             // Need to add file path to FileInfo
        stmt.setString(i++, fileData.getDescription());
        stmt.setLong(i++, fileData.getUploadMs());
        stmt.executeUpdate();
    }

    public void deleteFile(int fileId) throws SQLException {
        String sql = "DELETE FROM File WHERE fileId=" + fileId + ";";
        stmt.executeUpdate(sql);
    }

    public void close() throws SQLException {
        stmt.close();
    }

    private static FileInfo generateFileInfo(ResultSet rs) throws SQLException {
        int i = 1;
        return new FileInfo.Builder()
                .fileId(rs.getInt(i++))
                .userId(rs.getString(i++))
                .fileType(rs.getInt(i++))
                .contentType(rs.getInt(i++))
                .accessLevel(rs.getInt(i++))
                .title(rs.getString(i++))
                .course(rs.getString(i++))
                .courseRestricted(rs.getBoolean(i++))
                .path(rs.getString(i++))
                .description(rs.getString(i++))
                .uploadMs(rs.getLong(i++))
                .build();
    }

    public FileInfo getFileByFileId(int idFile) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT * FROM File WHERE fileId=" + idFile + ";");
        if ( rs.next() ) {
            return generateFileInfo(rs);
        }
        return null;
    }

    public List<FileInfo> getFilesByUserId(String idUser) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM File WHERE userId='" + idUser + "';");
        ArrayList<FileInfo> fileDataList = new ArrayList<>();

        while ( rs.next() ) {
            FileInfo fileData = generateFileInfo(rs);
            fileDataList.add(fileData);
        }
        return fileDataList;
    }

    public Set<Integer> getAllFileIds() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT fileId FROM File;");
        HashSet<Integer> fileIdSet = new HashSet<>();

        while ( rs.next() ) {
            fileIdSet.add( rs.getInt(1) );
        }
        return fileIdSet;
    }

}
