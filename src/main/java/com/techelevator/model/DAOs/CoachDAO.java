package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Coach;

public interface CoachDAO {

	Coach getCoachById(long id);
	
	List<Coach> getCoachList();
	
	void addCoach(String firstName, String lastName, long id);
	
	void removeCoach(long id);
	
	void updateName(String firstName, String lastName, long id);
	
	void updateLocation(String city, String state, long id);
	
	void updateAboutMe(String aboutMe, long id);
}