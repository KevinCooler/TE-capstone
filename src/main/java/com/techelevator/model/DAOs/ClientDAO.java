package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Client;

public interface ClientDAO {
	
	Client getClientById(long id);
	List<Client> getClientList();
	void addClient(String firstName, String lastName, long id);
	void removeClient(long id);
	void updateName(String firstName, String lastName, long id);
	void updateLocation(String city, String state, long id);
	void updateAboutMe(String aboutMe, long id);
	void updateIsLookingForCoach(boolean isLookingForCoach, long id);
}
