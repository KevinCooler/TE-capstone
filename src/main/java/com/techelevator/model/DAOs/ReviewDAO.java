package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Review;

public interface ReviewDAO {

	List<Review> getReviewList(long coachId);
	
	void addReview(long coachId, long clientId, int review, String reviewText);
	
	void removeReview(long reviewId);
	
	void removeReviewsByCoachId(long coachId);
}