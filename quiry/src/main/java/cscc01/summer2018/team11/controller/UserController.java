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
		System.out.println(body);
		boolean successStatus = UserService.createUser(body);
		System.out.println(successStatus);
		HashMap<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("userExists", successStatus);
		if(successStatus) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	


}
