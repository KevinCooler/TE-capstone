package com.techelevator.model;

public interface CoachDAO {

	Coach getCoachById(long id);
	
	void addCoach(String firstName, String lastName, long id);
	
	void removeCoach(long id);
	
	void updateName(String firstName, String lastName, long id);
	
	void updateLocation(String city, String state, long id);
	
	void updateAboutMe(String aboutMe, long id);
	
	boolean addCoachAvailability(int day, int hour);
}
