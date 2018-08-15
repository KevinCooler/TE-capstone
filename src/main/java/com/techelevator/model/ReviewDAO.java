package com.techelevator.model;

import java.util.List;

public interface ReviewDAO {

	List<Review> getReviewList(long coachId);
	
	void addReview(long coachId, long clientId, int review, String reviewText);
	
	void removeReview(long reviewId);
	
	void removeReviewsByCoachId(long coachId);
}