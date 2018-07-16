package cscc01.summer2018.team11.database;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDAO{
     Connection c;
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


     Statement stmt;

     
     
     public FileDAO(Connection c) throws SQLException{
          this.c = c;
          this.stmt = c.createStatement();

     }
     
     public void addFile(FileInfo3 fileData) throws SQLException{
          String sql = "INSERT INTO File (fileId, userId, fileType, contentType, accesslvl, title, course, courseRestricted, filePath, description) "
                 + "VALUES (?,?,?,?,?,?,?,?,?,?);";
         PreparedStatement stmt = c.prepareStatement(sql);
         int i = 0;
         stmt.setInt(i++, fileData.getFileId());
         stmt.setString(i++, fileData.getUserId()); // Need to add userId to to FileInfo
         stmt.setInt(i++, fileData.getFileType());
         stmt.setInt(i++, fileData.getContentType()); // Need to add content type variable to FileInfo
         stmt.setInt(i++, fileData.getAccessLevel());
         stmt.setString(i++, fileData.getTitle());
         stmt.setString(i++, fileData.getCourse());
         stmt.setBoolean(i++, fileData.isCourseRestricted()); // Need to add course restricted variable to FileInfo
         stmt.setString(i++, fileData.getFilePath()); // Need to add file path variable to FileInfo
         stmt.setString(i++, fileData.getDescription());
         
         stmt.executeUpdate(sql);
     }
     
     public void deleteFile(int fileId){
         String sql = "DROP * FROM INVOICE WHERE fileId=" + fileId + ";";
         stmt.executeUpdate(sql);
     }
     
     public void close() throws SQLException{
          stmt.close();
     }
     
     public FileInfo3 getFileByFileId(String fileId) throws SQLException{
        ResultSet rs = stmt.executeQuery( "SELECT * FROM INVOICE WHERE fileId=" + fileId + ";");
        
        rs.next();
        int i = 0;
		
        int id = rs.getInt(i++);
	    String userId= rs.getString(i++);
		int fileType = rs.getInt(i++);
		int contentType = rs.getInt(i++);
		int accessLevel = rs.getInt(i++);
		String title = rs.getString(i++);
		String course = rs.getString(i++);
		boolean courseRestricted = rs.getBoolean(i++);
		String filePath = rs.getString(i++);
		String description = rs.getString(i++);
		
		FileInfo3 fileData = new FileInfo3(id, userId, title, description, course,
				accessLevel, fileType, contentType, filePath, courseRestricted);
		
        
        return fileData;
     }
     
     public ArrayList<FileInfo3> getFilesByUserId(String idUser) throws SQLException{
         ResultSet rs = stmt.executeQuery( "SELECT * FROM INVOICE WHERE userId=" + idUser + ";");
         ArrayList<FileInfo3> fileDataList = new ArrayList<FileInfo3>();
         
         while(rs.next()) {
        	 int i = 0;
             int id = rs.getInt(i++);
     	     String userId= rs.getString(i++);
     		 int fileType = rs.getInt(i++);
     		 int contentType = rs.getInt(i++);
     		 int accessLevel = rs.getInt(i++);
     		 String title = rs.getString(i++);
     		 String course = rs.getString(i++);
     		 boolean courseRestricted = rs.getBoolean(i++);
     		 String filePath = rs.getString(i++);
     		 String description = rs.getString(i++);
     		 
     		 FileInfo3 fileData = new FileInfo3(id, userId, title, description, course,
    				accessLevel, fileType, contentType, filePath, courseRestricted);
     		 fileDataList.add(fileData);
     		 
     		 
         }
         
         return fileDataList;
      }
      
     

}
