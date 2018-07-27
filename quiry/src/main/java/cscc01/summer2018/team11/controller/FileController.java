package cscc01.summer2018.team11.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cscc01.summer2018.team11.file.FileGetter;
import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileStorage;
import cscc01.summer2018.team11.file.FileType;


@RestController
@RequestMapping(value = "/file")
public class FileController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(
			@RequestParam("file") MultipartFile remoteFile,
			@RequestParam("userId") String userId,
			@RequestParam("description") String description,
			@RequestParam("fileTitle") String title,
			@RequestParam("contentType") int contentType,
			@RequestParam("course") String course,
			@RequestParam("courseRestricted") String courseRestricted)
	{
		System.out.println(remoteFile);
		System.out.println(userId);
		System.out.println(description);
		System.out.println(title);
		System.out.println(contentType);
		System.out.println(course);

		// TODO: create fileInfo object
		FileInfo fileInfo = new FileInfo.Builder()
								.userId(userId)
								.description(description)
								.title(title)
								.contentType(contentType)
								.course(course)
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
			return ResponseEntity.badRequest().body(response);
		}

		// complete add file to database
		fileInfo.setPath(localFile.getAbsolutePath());
		FileStorage.addFile(fileInfo);

		// respond success
		return ResponseEntity.ok().body("success");
	}


	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFile(@RequestParam("fileId") int fileId) {
		FileInfo fileInfo = FileStorage.getFileInfo(fileId);
		File file = new File(fileInfo.getPath());

		MediaType mediaType;
		switch (fileInfo.getFileType()) {
		case FileType.PDF:
			mediaType = MediaType.APPLICATION_PDF;
			break;
		case FileType.HTML:
			mediaType = MediaType.TEXT_HTML;
			break;
		case FileType.TEXT:
			mediaType = MediaType.TEXT_PLAIN;
			break;
		default:
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setContentLength(file.length());
		headers.add("File-Name", file.getName());

		byte[] content = null;
		try {
			content = FileCopyUtils.copyToByteArray(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(content);
		}

		return ResponseEntity.ok().headers(headers).body(content);
	}

}
