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
	
	public boolean isNotCoach(User currentUser) {
		if(currentUser == null) return true;
		else if(currentUser.getRole().equals("coach")) return false;
		else return true;
	}
	
	public boolean isNotThisUser(User currentUser, long pageUserId) {
		if(currentUser == null) return true;
		else if(currentUser.getId() == pageUserId) return false;
		else return true;
	}
}
