package com.techelevator.security;

import com.techelevator.model.Objects.User;

public class PageAuthorizer {
	
	public PageAuthorizer() {
	}
	
	public boolean isNotAdmin(User currentUser) {
		if(currentUser == null) return true;
		else if(currentUser.getRole().equals("admin")) return false;
		else return true;
	}
}
