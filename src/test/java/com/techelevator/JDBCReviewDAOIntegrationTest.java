package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCReviewDAO;
import com.techelevator.model.Objects.Review;

public class JDBCReviewDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCReviewDAO reviewDAO;
	
	@Before
	public void setup() {
		reviewDAO = new JDBCReviewDAO(this.getDataSource());
	}
	
	@Test
	public void testAddingReview() {
		reviewDAO.addReview(4, 1, 4, "This is a test review.");
		
		List<Review> list = reviewDAO.getReviewList(4);
		
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testReviewDetails() {
		reviewDAO.addReview(4, 1, 4, "This is a test review.");
		List<Review> list = reviewDAO.getReviewList(4);
		
		Review review = list.get(0);
		
		Assert.assertEquals(4, review.getRating());
		Assert.assertEquals("This is a test review.", review.getReviewText());
	}
	
	@Test
	public void testRemovingReview() {
		reviewDAO.addReview(4, 1, 4, "This is a test review.");
		List<Review> list = reviewDAO.getReviewList(4);
		long id = list.get(0).getId();
		
		reviewDAO.removeReview(id);
		list = reviewDAO.getReviewList(4);
		
		Assert.assertEquals(0, list.size());
	}
}