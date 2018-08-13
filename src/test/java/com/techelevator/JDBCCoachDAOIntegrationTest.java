package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.Coach;
import com.techelevator.model.JDBCCoachDAO;

//import com.techelevator.model.JDBCUserDAO;

/* To get these tests to run, this dummy user needs to be added into the app_user table.
 * If this isn's the first user in your table, you'll need to manually adjust the ids in the tests
 * 
 insert into app_user (user_name, password, role, salt) VALUES ('testing1', 'testing', 'coach', 'llll');
 */

public class JDBCCoachDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCCoachDAO coachDao;
	//private JDBCUserDAO userDAO;
	
	@Before
	public void setup() {
		coachDao = new JDBCCoachDAO(super.getDataSource());
		coachDao.addCoach("John", "Doe", 1);
	}
	
	@Test
	public void testCoachAddition() {
		Coach coach = coachDao.getCoachById(1);
		Assert.assertEquals("John", coach.getFirstName());
		Assert.assertEquals("Doe", coach.getLastName());
		Assert.assertEquals(1, coach.getId());
	}
	
	@Test
	public void testRemoveCoach() {
		Coach coach = coachDao.getCoachById(1);
		Assert.assertEquals("John", coach.getFirstName());
		
		coachDao.removeCoach(1);
		coach = coachDao.getCoachById(1);
		
		Assert.assertEquals(null, coach);
	}
	
	@Test
	public void testUpdateName() {
		Coach coach = coachDao.getCoachById(1);
		Assert.assertEquals("John", coach.getFirstName());
		Assert.assertEquals("Doe", coach.getLastName());
		
		coachDao.updateName("Joey", "Jenkins", 1);
		
		coach = coachDao.getCoachById(1);
		Assert.assertEquals("Joey", coach.getFirstName());
		Assert.assertEquals("Jenkins", coach.getLastName());
	}
	
	@Test
	public void testUpdateLocation() {
		Coach coach = coachDao.getCoachById(1);
		Assert.assertEquals("update", coach.getCity());
		Assert.assertEquals("update", coach.getState());
		
		coachDao.updateLocation("Columbus", "OH", 1);
		
		coach = coachDao.getCoachById(1);
		Assert.assertEquals("Columbus", coach.getCity());
		Assert.assertEquals("OH", coach.getState());
	}
	
	@Test
	public void updateAboutMe() {
		Coach coach = coachDao.getCoachById(1);
		Assert.assertEquals("update", coach.getAboutMe());
		
		coachDao.updateAboutMe("I'm a test coach", 1);
		
		coach = coachDao.getCoachById(1);
		Assert.assertEquals("I'm a test coach", coach.getAboutMe());
	}
}