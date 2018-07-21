package cscc01.summer2018.team11.controller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import cscc01.summer2018.team11.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Boolean>> registerUser(@RequestBody HashMap<String, String> body){
		boolean successStatus = UserService.createUser(body);
		HashMap<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("userExists", successStatus);
		if(successStatus) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	
	public HashMap<String, String> getSomething() {
		HashMap<String, String> users = new HashMap<String, String>();
		users.put("nice", "meme");
		users.put("testing", "incase");
		users.put("n4", "mee");
		users.put("ng", "ise");
		users.put("resce", "memess");
		users.put("kolng", "llse");

		return users;
	}

}
