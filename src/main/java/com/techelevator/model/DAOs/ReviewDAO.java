package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Review;

public interface ReviewDAO {

	List<Review> getReviewList(long coachId);
	
	long addReview(long coachId, long clientId, int review, String reviewText);
	
	void editReview(long reviewId, long clientId, int rating, String reviewText);
	
	void removeReview(long reviewId);
	
	void removeReviewsByCoachId(long coachId);
}