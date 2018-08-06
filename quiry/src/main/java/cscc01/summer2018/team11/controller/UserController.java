package cscc01.summer2018.team11.controller;


import java.sql.SQLException;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cscc01.summer2018.team11.activation.MailService;
import cscc01.summer2018.team11.exception.UserExistsException;
import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private MailService mailer;
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> registerUser(@RequestBody HashMap<String, String> body) {
		body.put("verification", UserService.generateVerificationCode());
		System.out.println(body);
		boolean userExists = false;
		try {
			UserService.createUser(body);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (UserExistsException e) {
			userExists = true;
			e.printStackTrace();
		}
		System.out.println(userExists);
		if (!userExists) {
			String userId = body.get("userId");
			try {
				mailer.sendActivationEmail(UserService.getUser(userId));
				return ResponseEntity.status(HttpStatus.OK).body(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("userExists", (userExists ? "true" : "false"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> loginUser(@RequestBody HashMap<String, String> body) {
		System.out.println(body);
		User userData = UserService.loginUser(body);
		if (userData != null) {
			return ResponseEntity.status(HttpStatus.OK).body(UserService.parseUser(userData));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> modifyUser(@RequestBody HashMap<String, String> body) {
		System.out.println(body);

		User userData = UserService.loginUser(body);
		if (userData == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		String firstName = body.get("firstName");
		if (firstName != null && !firstName.isEmpty()) {
			userData.setFirstName(firstName);
		}

		String lastName = body.get("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			userData.setLastName(lastName);
		}

		String email = body.get("email");
		if (email != null && !lastName.isEmpty()) {
			userData.setEmail(email);
		}

		String password = body.get("newPassword");
		if (password != null && !password.isEmpty()) {
			userData.setPassword(password);
		}

		if (!UserService.updateUser(userData)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(UserService.parseUser(userData));
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activateUser(@RequestParam("userId") String userId, @RequestParam("verification") String verification) {
		
		User user = UserService.getUser(userId);
		if (user != null && verification.equals(user.getVerification())) {
			user.setVerification("activated");
			UserService.updateUser(user);
			return new ModelAndView("redirect:http://localhost:8080/activation-success.html");
		}
		return new ModelAndView("redirect:http://localhost:8080/activation-failure.html");
	}

}
