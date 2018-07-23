package cscc01.summer2018.team11.controller;


import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cscc01.summer2018.team11.service.UserService;
import cscc01.summer2018.team11.user.User;


@RestController
@RequestMapping(value = "/file")
public class FileController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> uploadFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("userId") String userId,
			@RequestParam("description") String description,
			@RequestParam("fileTitle") String fileTitle,
			@RequestParam("fileType") String fileType,
			@RequestParam("contentType") String contentType,
			@RequestParam("courseRestricted") String courseRestricted)
	{
		System.out.println(file);
		System.out.println(userId);
		System.out.println(description);
		System.out.println(fileTitle);
		System.out.println(fileType);
		System.out.println(contentType);
		System.out.println(courseRestricted);
		return null;
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
