package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCMessageDAO;
import com.techelevator.model.Objects.Message;

public class JDBCMessageDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCMessageDAO messageDao;
	
	@Before
	public void setup() {
		messageDao = new JDBCMessageDAO(this.getDataSource());
	}
	
	@Test
	public void testAddingMessages() {
		messageDao.addMessage(7, 4, "This is a test message");
		List<Message> list = messageDao.getMessages(4, true);
		
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testClientMessages() {
		messageDao.addMessage(7, 4, "Test Message 1");
		messageDao.addMessage(7, 4, "Test Message 2");
		messageDao.addMessage(7, 4, "Test Message 3");
		
		List<Message> list = messageDao.getMessages(7, false);
		
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testMessageContents() {
		messageDao.addMessage(7, 4, "Test Message 1");
		messageDao.addMessage(7, 4, "Test Message 2");
		messageDao.addMessage(7, 4, "Test Message 3");
		
		List<Message> list = messageDao.getMessages(4, true);
		
		Assert.assertEquals("Test Message 1", list.get(0).getMessageText());
		Assert.assertEquals("Test Message 2", list.get(1).getMessageText());
		Assert.assertEquals("Test Message 3", list.get(2).getMessageText());
	}
	
	@Test
	public void removeMessages() {
		messageDao.addMessage(7, 4, "Test Message 1");
		messageDao.addMessage(7, 4, "Test Message 2");
		messageDao.addMessage(7, 4, "Test Message 3");
		
		List<Message> list = messageDao.getMessages(4, true);
		Assert.assertEquals(3, list.size());
		
		messageDao.removeMessage(list.get(0).getId());
		list = messageDao.getMessages(4, true);
		Assert.assertEquals(2, list.size());
		
		messageDao.removeMessage(list.get(0).getId());
		list = messageDao.getMessages(4, true);
		Assert.assertEquals(1, list.size());
		
		messageDao.removeMessage(list.get(0).getId());
		list = messageDao.getMessages(4, true);
		Assert.assertEquals(0, list.size());
	}
}