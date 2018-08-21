package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCReviewDAO;
import com.techelevator.model.Objects.Review;

public class JDBCReviewDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCReviewDAO reviewDAO;
	private TestingUtilities util;
	private long coachId;
	private long clientId;
	
	@Before
	public void setup() {
		reviewDAO = new JDBCReviewDAO(super.getDataSource());
		util = new TestingUtilities(super.getDataSource());
		
		coachId = util.newCoach("Test", "Coach");
		clientId = util.newClient("Test", "Client");
	}
	
	@Test
	public void testAddingReview() {
		reviewDAO.addReview(coachId, clientId, 4, "Test Review");
		
		List<Review> list = reviewDAO.getReviewList(coachId);
		
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testReviewContents() {
		reviewDAO.addReview(coachId, clientId, 4, "Test Review");
		Review review = reviewDAO.getReviewList(coachId).get(0);
		
		Assert.assertEquals(4, review.getRating());
		Assert.assertEquals("Test Review", review.getReviewText());
	}
	
	@Test
	public void testReviewList() {
		reviewDAO.addReview(coachId, clientId, 4, "Test Review 1");
		reviewDAO.addReview(coachId, clientId, 4, "Test Review 2");
		reviewDAO.addReview(coachId, clientId, 4, "Test Review 3");
		
		List<Review> list = reviewDAO.getReviewList(coachId);
		
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testRemovingReview() {
		long reviewId = reviewDAO.addReview(coachId, clientId, 4, "Test Review");
		List<Review> list = reviewDAO.getReviewList(coachId);
		Assert.assertEquals(1, list.size());
		
		reviewDAO.removeReview(reviewId);
		list = reviewDAO.getReviewList(coachId);
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void testRemovingReviewsByCoachId() {
		reviewDAO.addReview(coachId, clientId, 4, "Test Review 1");
		reviewDAO.addReview(coachId, clientId, 4, "Test Review 2");
		reviewDAO.addReview(coachId, clientId, 4, "Test Review 3");
		List<Review> list = reviewDAO.getReviewList(coachId);
		Assert.assertEquals(3, list.size());
		
		reviewDAO.removeReviewsByCoachId(coachId);
		list = reviewDAO.getReviewList(coachId);
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void testEditingReview() {
		long reviewId = reviewDAO.addReview(coachId, clientId, 4, "Test Review");
		Review review = reviewDAO.getReviewList(coachId).get(0);
		Assert.assertEquals(4, review.getRating());
		Assert.assertEquals("Test Review", review.getReviewText());
		
		reviewDAO.editReview(reviewId, clientId, 2, "Updated Review");
		review = reviewDAO.getReviewList(coachId).get(0);
		Assert.assertEquals(2, review.getRating());
		Assert.assertEquals("Updated Review", review.getReviewText());
	}
}