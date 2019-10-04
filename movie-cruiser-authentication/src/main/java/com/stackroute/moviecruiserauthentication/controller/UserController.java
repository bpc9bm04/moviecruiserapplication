package com.stackroute.moviecruiserauthentication.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserauthentication.domain.User;
import com.stackroute.moviecruiserauthentication.exception.UserExistException;
import com.stackroute.moviecruiserauthentication.service.SecurityTokenGenerator;
import com.stackroute.moviecruiserauthentication.service.UserService;

/**
 * Controller class to control the request mapping flow
 * @RequestMapping used to route the http request which has the format "/api/v1/userservice" 
 * @author Tarun K Tiwari
 *
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/userservice")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;
	Map<String, String> tokenMap = new HashMap<>();
	@PostMapping("/register")
	ResponseEntity<?> registerUser(@RequestBody User newUser) {
		try {
			userService.saveUser(newUser);
			return new ResponseEntity<String>("{ \"message\": \"" + "User registered successfully!" + "\"}", HttpStatus.CREATED);
		} catch (UserExistException uee) {
			return new ResponseEntity<String>("{ \"message\": \"" + uee.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	ResponseEntity<?> loginUser(@RequestBody User loginUser) {
		try {
			String userId = loginUser.getUserId();
			String password = loginUser.getPassword();

			if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)) {
				throw new Exception("User id or password is empty!");
			}

			User user = userService.findByUserIdAndPassword(userId, password);

			if (Objects.isNull(user)) {
				throw new Exception("User with given id doesn't exists!");
			}
			if (!password.equals(user.getPassword())) {
				throw new Exception("Invalid login credential, please try again!");
			}
			tokenMap = securityTokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(tokenMap, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/isAuthenticated")
	public ResponseEntity<?> isUserAuthenticated(HttpServletRequest request) {
		System.out.println("is authenticate");
		String authHeader = request.getHeader("Authorization");
		System.out.println("authHeader" + authHeader);
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			System.out.println("hello");
			tokenMap.put("isAuthenticated", "false");
		} else {
		String token = authHeader.substring(7);
		System.out.println("tokenMap" + tokenMap.get("token"));
		System.out.println("token" + token);
		if(token.equals(tokenMap.get("token"))) {
			//tokenMap.clear();
			tokenMap.put("isAuthenticated", "true");
		}else {
			//tokenMap.clear();
			tokenMap.put("isAuthenticated", "false");
		}
	}
		return new ResponseEntity<>(tokenMap, HttpStatus.OK);
	}

}
