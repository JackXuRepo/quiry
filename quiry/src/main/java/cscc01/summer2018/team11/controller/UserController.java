package cscc01.summer2018.team11.controller;


import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.UserStorage;


@RestController
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> registerUser(@RequestBody HashMap<String, String> body){
		System.out.println(body);
		boolean userExists = UserStorage.createUser(body);
		System.out.println(userExists);
		if(!userExists) {
			User newUser = UserStorage.loginUser(body);
			return ResponseEntity.status(HttpStatus.OK).body(UserStorage.parseUser(newUser));
		}
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("userExists", (userExists ? "true" : "false"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> loginUser(@RequestBody HashMap<String, String> body){
		System.out.println(body);
		User userData = UserStorage.loginUser(body);
		System.out.println(UserStorage.parseUser(userData));
		if(userData != null) {
			return ResponseEntity.status(HttpStatus.OK).body(UserStorage.parseUser(userData));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

}
