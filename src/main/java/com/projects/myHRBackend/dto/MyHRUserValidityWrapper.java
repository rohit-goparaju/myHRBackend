package com.projects.myHRBackend.dto;

import com.projects.myHRBackend.enums.UserValidity;
import com.projects.myHRBackend.modal.MyHRUser;

public class MyHRUserValidityWrapper {
	private MyHRUser user;
	private UserValidity validity;
	
		
	public MyHRUserValidityWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MyHRUserValidityWrapper(MyHRUser user, UserValidity validity) {
		super();
		this.user = user;
		this.validity = validity;
	}


	public MyHRUser getUser() {
		return user;
	}


	public void setUser(MyHRUser user) {
		this.user = user;
	}


	public UserValidity getValidity() {
		return validity;
	}


	public void setValidity(UserValidity validity) {
		this.validity = validity;
	}

	@Override
	public String toString() {
		return "MyHRUserValidityWrapper [user=" + user + ", validity=" + validity + "]";
	}
}
