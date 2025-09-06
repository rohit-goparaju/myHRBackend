package com.projects.myHRBackend.modal;

import com.projects.myHRBackend.enums.MyHRUserLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MyHRUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MyHRUserLevel level = MyHRUserLevel.EMPLOYEE;
	public MyHRUser() {
		super();
	}
	
	public MyHRUser(int id, String username, String password, MyHRUserLevel level) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.level = level;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public MyHRUserLevel getLevel() {
		return level;
	}

	public void setLevel(MyHRUserLevel level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "MyHRUser [id=" + id + ", username=" + username + ", password=" + password + ", level=" + level + "]";
	}

}
