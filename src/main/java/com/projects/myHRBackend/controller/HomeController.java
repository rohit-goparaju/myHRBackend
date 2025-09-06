package com.projects.myHRBackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myHRBackend.dto.Credentials;
import com.projects.myHRBackend.dto.MyHRUserValidityWrapper;
import com.projects.myHRBackend.enums.UserValidity;
import com.projects.myHRBackend.modal.MyHRUser;
import com.projects.myHRBackend.service.MyHRUserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class HomeController {
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
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
	public ResponseEntity<MyHRUserValidityWrapper> test(HttpSession session, @RequestBody Credentials credentials) {
		System.out.println(credentials);
		
		UserValidity validity = authenticateUser(credentials);
		MyHRUserValidityWrapper userValidityWrapper = new MyHRUserValidityWrapper();
		userValidityWrapper.setValidity(validity);
		if(validity == UserValidity.VALID) {
			MyHRUser user = service.findByUsername(credentials.getUsername()).orElse(null);
			session.setAttribute("user", user);
			userValidityWrapper.setUser(user);
			log.info("login successful {} ", credentials.getUsername());
		}
		
		return ResponseEntity.ok(userValidityWrapper);		
	}
	
	@PostMapping("/Logout")
	public ResponseEntity<String> logout(HttpSession session) {
		log.info("logged out successfully");
		session.invalidate();
		return ResponseEntity.ok("logged out");
	}
	
	
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
