package com.projects.myHRBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projects.myHRBackend.modal.MyHRUser;
import com.projects.myHRBackend.repo.MyHRUserRepo;

@Service
public class MyHRUserService {
	
	private final MyHRUserRepo repo;

	public MyHRUserService(MyHRUserRepo repo) {
		super();
		this.repo = repo;
	}
	
	public Optional<MyHRUser> findByUsername(String username){
		return repo.findByUsername(username);
	}
	
	public MyHRUser addUser(MyHRUser user){
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
		user.setPassword(bcrypt.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public List<MyHRUser> findAllUsers(){
		return repo.findAll();
	}
}
