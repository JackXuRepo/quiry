package cscc01.summer2018.team11.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cscc01.summer2018.team11.file.FileGetter;
import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileStorage;
import cscc01.summer2018.team11.service.UserService;
import cscc01.summer2018.team11.user.User;


@RestController
@RequestMapping(value = "/file")
public class FileController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(
			@RequestParam("file") MultipartFile remoteFile,
			@RequestParam("userId") String userId,
			@RequestParam("description") String description,
			@RequestParam("fileTitle") String title,
			@RequestParam("contentType") String contentType,
			@RequestParam("courseRestricted") String courseRestricted)
	{
		System.out.println(remoteFile);
		System.out.println(userId);
		System.out.println(description);
		System.out.println(title);
		System.out.println(contentType);
		System.out.println(courseRestricted);

		// TODO: create fileInfo object
		FileInfo fileInfo = new FileInfo.Builder()
								.userId(userId)
								.description(description)
								.title(title)
								.contentType(Integer.parseInt(contentType))
								.courseRestricted(courseRestricted.equals(1) ? true : false)
								.build();
		File localFile = FileGetter.createFile(fileInfo.getFileId(), remoteFile.getOriginalFilename());

		/* attempt upload
		 * doesn't work - returns temp directory
		try {
			remoteFile.transferTo(localFile);
		} catch (IllegalStateException | IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			String response = ex.getMessage();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		 */

		// attempt upload
		String response = "size mismatch";

		try {
			FileOutputStream fos = new FileOutputStream(localFile);
			fos.write(remoteFile.getBytes());
			fos.close();
		} catch (Exception ex) {
			FileGetter.deleteFile(fileInfo.getFileId());
			// respond error message
			ex.printStackTrace();
			response = ex.getMessage();
		}

		// check file size
		if (remoteFile.getSize() != localFile.length()) {
			FileGetter.deleteFile(fileInfo.getFileId());
			System.out.println(response);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// complete add file to database
		fileInfo.setPath(localFile.getAbsolutePath());
		FileStorage.addFile(fileInfo);

		// respond success
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> loginUser(@RequestBody HashMap<String, String> body){
		System.out.println(body);
		User userData = UserService.loginUser(body);
		System.out.println(UserService.parseUser(userData));
		if(userData != null) {
			return ResponseEntity.status(HttpStatus.OK).body(UserService.parseUser(userData));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

}
