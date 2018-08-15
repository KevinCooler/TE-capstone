package com.techelevator.model;

import java.util.List;

public interface ClientDAO {
	
	Client getClientById(long id);
	List<Client> getClientList();
	void addClient(String firstName, String lastName, long id);
	void removeClient(long id);
	void updateName(String firstName, String lastName, long id);
	void updateLocation(String city, String state, long id);
	void updateAboutMe(String aboutMe, long id);
	

}
