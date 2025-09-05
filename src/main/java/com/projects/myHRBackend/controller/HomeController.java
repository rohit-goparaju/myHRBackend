package com.projects.myHRBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myHRBackend.Credentials;
import com.projects.myHRBackend.modal.MyHRUser;
import com.projects.myHRBackend.service.MyHRUserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class HomeController {
	private enum UserValidity{
		VALID, INVALID
	};
	
	public final MyHRUserService service;
	
	public HomeController(MyHRUserService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/")
	public ResponseEntity<String> backendHome(HttpSession session) {
		String body = "";
		if(session.getAttribute("user") != null)
			body = "Home page";
		else
			body = "Invalid user";
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/Login")
	public ResponseEntity<String> test(HttpSession session, @RequestBody Credentials credentials) {
		System.out.println(credentials);
		
		UserValidity validity = authenticateUser(credentials);
		String validityString = validity.name().toLowerCase();
		
		if(validity == UserValidity.VALID) {
			session.setAttribute("user", service.findByUsername(credentials.getUsername()));
		}
		
		return ResponseEntity.ok(validityString);
	}
	
	@PostMapping("/Logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("logged out");
	}
	
//	@GetMapping("/createSuperRoot")
//	public String test() {
//		MyHRUser superRoot = new MyHRUser();
//		superRoot.setUsername("rohit@myHR.in");
//		superRoot.setPassword("Test@123");
//		service.addUser(superRoot);
//		return "created";
//	}
	
	private UserValidity authenticateUser(Credentials credentials) {
		MyHRUser user = service.findByUsername(credentials.getUsername()).orElse(null);
		
		if(user == null)
			return UserValidity.INVALID;
		else {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
			if(bcrypt.matches(credentials.getPassword(), user.getPassword()))
				return UserValidity.VALID;
			else
				return UserValidity.INVALID;
		}		
	}
}
