package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.JDBCDAOs.JDBCClientDAO;
import com.techelevator.model.JDBCDAOs.JDBCFeedbackDAO;
import com.techelevator.model.JDBCDAOs.JDBCUserDAO;
import com.techelevator.model.Objects.Feedback;
import com.techelevator.security.PasswordHasher;


public class JDBCFeedbackDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCFeedbackDAO feedbackDAO;
	private JDBCUserDAO userDAO;
	private PasswordHasher hashMaster;
	private JDBCClientDAO clientDAO;
	
	private long clientId;
	
	@Before
	public void setup() {
		feedbackDAO = new JDBCFeedbackDAO(getDataSource());
		clientDAO = new JDBCClientDAO(super.getDataSource());
		hashMaster = new PasswordHasher();
		userDAO = new JDBCUserDAO(super.getDataSource(), hashMaster);
		clientId = userDAO.saveUser("testUsername",  "testPassword",  "testRole");
		clientDAO.addClient("testFirstName", "testlastName", clientId);
	}
	
	@Test
	public void returns_number_of_reviews_increased_by_one_after_add() {
		int initialReviewCount = feedbackDAO.getFeedbackByClientId(clientId).size();
		feedbackDAO.addFeedback(clientId, 1,  "testDetail");
		int finalReviewCount = feedbackDAO.getFeedbackByClientId(clientId).size();
		Assert.assertEquals(finalReviewCount,  initialReviewCount + 1);
	}
	
	@Test
	public void returns_original_list_size_after_add_and_delete() {
		int initialReviewCount = feedbackDAO.getFeedbackByClientId(clientId).size();
		long feedbackId = feedbackDAO.addFeedback(clientId, 1,  "testDetail");
		feedbackDAO.removeFeedback(feedbackId);
		int finalReviewCount = feedbackDAO.getFeedbackByClientId(clientId).size();
		Assert.assertEquals(initialReviewCount, finalReviewCount);
	}
	
	@Test
	public void returns_feedback_that_was_saved() {
		long feedbackId = feedbackDAO.addFeedback(clientId, 1,  "testDetail");
		Feedback feedback = feedbackDAO.getFeedbackById(feedbackId);
		Assert.assertEquals("testDetail",  feedback.getDetail());
		Assert.assertEquals(clientId, feedback.getClientId());
	}
	
//	@Test
//	public void returns_updated_detail() {
//		long feedbackId = feedbackDAO.addFeedback(clientId, 1,  "testDetail");
//		feedbackDAO.updateFeedback(feedbackId, "updatedDetail");
//		Feedback feedback = feedbackDAO.getFeedbackById(feedbackId);
//		Assert.assertEquals("updatedDetail",  feedback.getDetail());
//		
//	}
}