package com.techelevator.model.DAOs;

import java.util.List;

import com.techelevator.model.Objects.Message;

public interface MessageDAO {

	List<Message> getMessages(long userId);
	
	Message viewMessage(long messageId, long userId);
	
	void addMessage(long senderId, String senderName, 
			long receiverId, String receiverName, String messageText);
	
	void removeMessage(long messageId);
}
