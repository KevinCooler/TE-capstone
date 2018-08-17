package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Feedback;

public interface FeedbackDAO {
	
	public List<Feedback> getFeedbackByClientId(long clientId);
	
	public long addFeedback(long clientId, int module, String detail);
	
	public void removeFeedback(long feedbackId);
	
	public void updateFeedback(String detail, long clientId, int module);
	
	public Feedback getFeedbackById(long feedbackId);
	
	public void createClientFeedback(long clientId);

}
