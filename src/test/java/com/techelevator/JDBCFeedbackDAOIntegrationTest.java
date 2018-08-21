package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCFeedbackDAO;
import com.techelevator.model.Objects.Feedback;

public class JDBCFeedbackDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCFeedbackDAO feedbackDAO;
	private TestingUtilities util;
	
	private long clientId;
	
	@Before
	public void setup() {
		feedbackDAO = new JDBCFeedbackDAO(super.getDataSource());
		util = new TestingUtilities(super.getDataSource());
		clientId = util.newClient("testFirstName", "testlastName");
	}
	
	@Test
	public void testAddingFeedback() {
		long feedbackId = feedbackDAO.addFeedback(clientId, 1, "Test feedback");
		Feedback feed = feedbackDAO.getFeedbackById(feedbackId);
		
		Assert.assertEquals("Test feedback", feed.getDetail());
	}
	
	@Test
	public void testRemovingFeedback() {
		long feedbackId = feedbackDAO.addFeedback(clientId, 1, "Test feedback");
		Feedback feed = feedbackDAO.getFeedbackById(feedbackId);
		Assert.assertEquals("Test feedback", feed.getDetail());
		
		feedbackDAO.removeFeedback(feedbackId);
		feed = feedbackDAO.getFeedbackById(feedbackId);
		Assert.assertEquals(null, feed);
	}
	
	@Test
	public void testUpdatingFeedback() {
		long feedbackId = feedbackDAO.addFeedback(clientId, 1, "Test feedback");
		Feedback feed = feedbackDAO.getFeedbackById(feedbackId);
		Assert.assertEquals("Test feedback", feed.getDetail());
		
		feedbackDAO.updateFeedback("Updated feedback", clientId, 1);
		feed = feedbackDAO.getFeedbackById(feedbackId);
		Assert.assertEquals("Updated feedback", feed.getDetail());
	}
	
	@Test
	public void testGettingFeedbackByClientId() {
		feedbackDAO.addFeedback(clientId, 1, "Test feedback 1");
		feedbackDAO.addFeedback(clientId, 2, "Test feedback 2");
		feedbackDAO.addFeedback(clientId, 3, "Test feedback 3");
		List<Feedback> list = feedbackDAO.getFeedbackByClientId(clientId);
		
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testCreatingFeedbackByClientId() {
		feedbackDAO.createClientFeedback(clientId);
		List<Feedback> list = feedbackDAO.getFeedbackByClientId(clientId);
		
		Assert.assertEquals(12, list.size());
	}
}