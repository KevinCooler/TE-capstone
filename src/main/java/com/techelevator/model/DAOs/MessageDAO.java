package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Message;

public interface MessageDAO {

	List<Message> getMessages(long userId, boolean isCoach);
	
	Message viewMessage(long messageId);
	
	void addMessage(long clientId, long coachId, String messageText);
	
	void removeMessage(long messageId);
}
