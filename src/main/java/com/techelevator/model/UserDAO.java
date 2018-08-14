package com.techelevator.model;

public interface UserDAO {

	public Long saveUser(String userName, String password, String role);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public User getUserByUserName(String userName);
	
	public void deleteUserByUserId(Long userId);

}
