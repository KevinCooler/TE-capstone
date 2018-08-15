package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Feedback;

public interface FeedbackDAO {
	
	public List<Feedback> getFeedbackByClientId(long clientId);
	
	public long addFeedback(long clientId, int module, String detail);
	
	public void removeFeedback(long feedbackId);
	
	public void updateFeedback(long feedbackId, String detail);
	
	public Feedback getFeedbackById(long feedbackId);

}
