package cscc01.summer2018.team11;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import cscc01.summer2018.team11.database.Database;
import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.file.FileInfo2;
import cscc01.summer2018.team11.file.FileStorage;

public class FileDAOTest {

	public static void main(String[] args) throws SQLException {
		FileStorage.initialize();

		int fileId = FileInfo2.generateId();
		String userId = "xuhaosen";
		String title = "Assignment 3 Solution";
		String description = "This is the solutions for Assingment 3";
		String course = "CSCC01";
		int accessLevel = 1;
		int fileType = 1;
		int contentType = 1;
		String filePath = "C:/Nice/A3.pdf";
		boolean courseRestricted = true;
		long uploadMs = System.currentTimeMillis();

		// Create a user row
		String sql = "INSERT INTO User (userId, firstName, lastName, email, password, accesslvl) "
				+ "VALUES (?,?,?,?,?,?);";
		PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
		int i = 1;
		stmt.setString(i++, "xuhaosen");
		stmt.setString(i++, "Jack");						// Need to add userId to to FileInfo
		stmt.setString(i++, "Xu");
		stmt.setString(i++, "haosen.xu@mail.utoronto.ca");	// Need to add content type variable to FileInfo
		stmt.setString(i++, "password");
		stmt.setInt(i++, 1);
		// stmt.executeUpdate();

		// Test fileDAO
		FileInfo2 fileData = new FileInfo2(userId, title, description, contentType,
				accessLevel, filePath, course, courseRestricted, fileType, uploadMs, fileId);

		FileDAO fileDatabase = new FileDAO();
		fileDatabase.addFile(fileData);

		FileInfo2 file = fileDatabase.getFileByFileId(fileId);
		System.out.println(file.getId());
		System.out.println(fileDatabase.getAllFileIds());
	}

}
