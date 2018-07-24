package cscc01.summer2018.team11;


import java.sql.SQLException;

import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileStorage;


/**
 * Test FileDAO
 */
public class FileDAOTest {

	public static void main(String[] args) throws SQLException {
		FileStorage.initialize();

		int fileId = FileInfo.generateId();
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

//		FileInfo fileData = new FileInfo(userId, title, description, contentType,
//				accessLevel, filePath, course, courseRestricted, fileType, uploadMs, fileId);

		FileDAO fileDatabase = new FileDAO();
//		fileDatabase.updateFile(fileData);

		FileInfo file = fileDatabase.getFileByFileId(fileId);
		System.out.println(file.getId());
		System.out.println(fileDatabase.getAllFileIds());
	}

}
