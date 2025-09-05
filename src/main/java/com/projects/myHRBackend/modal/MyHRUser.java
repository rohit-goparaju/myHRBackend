package com.projects.myHRBackend.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	public MyHRUser() {
		super();
	}
	public MyHRUser(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
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
	@Override
	public String toString() {
		return "MyHRUser [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}
