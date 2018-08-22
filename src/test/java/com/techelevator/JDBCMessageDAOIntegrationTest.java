package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCMessageDAO;
import com.techelevator.model.Objects.Message;

public class JDBCMessageDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCMessageDAO messageDao;
	private TestingUtilities util;
	private long senderId;
	private long receiverId;
	
	@Before
	public void setup() {
		messageDao = new JDBCMessageDAO(this.getDataSource());
		util = new TestingUtilities(super.getDataSource());
		
		senderId = util.newClient("Test", "Client");
		receiverId = util.newCoach("Test", "Coach");
	}
	
	@Test
	public void testAddMessage() {
		long messageId = 
				messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test");
		
		Message message = messageDao.viewMessage(messageId, senderId);
		
		Assert.assertEquals("Test", message.getMessageText());
	}
	
	@Test
	public void testRemoveMessage() {
		long messageId = 
				messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test");
		Message message = messageDao.viewMessage(messageId, senderId);
		Assert.assertEquals("Test", message.getMessageText());
		
		messageDao.removeMessage(messageId);
		message = messageDao.viewMessage(messageId, senderId);
		Assert.assertEquals(null, message);
	}
	
	@Test
	public void testGetMessages() {
		messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test 1");
		messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test 2");
		messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test 3");
		
		List<Message> list = messageDao.getMessages(receiverId);
		
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testRemovingMessagesByUserId() {
		messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test 1");
		messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test 2");
		messageDao.addMessage(senderId, "Test Sender", receiverId, "Test Receiver", "Test 3");
		List<Message> list = messageDao.getMessages(receiverId);
		Assert.assertEquals(3, list.size());
		
		messageDao.removeMessagesByUserId(senderId);
		list = messageDao.getMessages(senderId);
		Assert.assertEquals(0, list.size());
	}
}