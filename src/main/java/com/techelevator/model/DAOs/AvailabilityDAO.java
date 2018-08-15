package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Availability;

public interface AvailabilityDAO {

	List<Availability> getAvailabilityList(long coachId);
	
	void addAvailability(long coachId, int day, int hourStart, int hourEnd);
	
	void removeAvailability(long id);
	
	void removeAvailabilityByCoachId(long coachId);
}
