package com.techelevator.model;

import java.util.List;

public interface AvailabilityDAO {

	List<Availability> getAvailabilityList(long coachId);
	
	void addAvailability(long coachId, int day, int hourStart, int hourEnd);
	
	void removeAvailability(long id);
	
	void removeAvailabilityByCoachId(long coachId);
}
