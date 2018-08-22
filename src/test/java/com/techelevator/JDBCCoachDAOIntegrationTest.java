package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCClientDAO;
import com.techelevator.model.JDBCDAOs.JDBCCoachDAO;
import com.techelevator.model.Objects.Coach;

public class JDBCCoachDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCCoachDAO coachDao;
	private long coachIdOne;
	private long coachIdTwo;
	private long coachIdThree;
	private TestingUtilities util;
	private JDBCClientDAO clientDAO;
	
	@Before
	public void setup() {
		coachDao = new JDBCCoachDAO(super.getDataSource());
		clientDAO = new JDBCClientDAO(super.getDataSource());
		util = new TestingUtilities(super.getDataSource());
		coachIdOne = util.newUser("test1");
		coachDao.addCoach("John", "Doe", coachIdOne);
		
	}
	
	@Test
	public void returns_true_when_coach_has_client() {
		long clientId = util.newClient("client",  "paried");
		clientDAO.assignCoach(clientId, coachIdOne);
		Assert.assertTrue(coachDao.hasPairedClients(coachIdOne));
	}
	
	@Test
	public void returns_false_when_coach_does_not_have_client() {
		Assert.assertFalse(coachDao.hasPairedClients(coachIdOne));
	}
	
	@Test
	public void testCoachAddition() {
		Coach coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("John", coach.getFirstName());
		Assert.assertEquals("Doe", coach.getLastName());
		Assert.assertEquals(coachIdOne, coach.getId());
	}
	
	@Test
	public void testCoachList() {
		List<Coach> list = coachDao.getCoachList();
		int listSize = list.size();
		
		coachIdTwo = util.newUser("test2");
		coachIdThree = util.newUser("test3");
		coachDao.addCoach("Jane", "Doe", coachIdTwo);
		coachDao.addCoach("Jim", "Doe", coachIdThree);
		
		list = coachDao.getCoachList();
		Assert.assertEquals(listSize + 2, list.size());
	}
	
	@Test
	public void testContentsOfCoachList() {
		coachIdTwo = util.newUser("test2");
		coachIdThree = util.newUser("test3");
		coachDao.addCoach("Jane", "Doe", coachIdTwo);
		coachDao.addCoach("Jim", "Doe", coachIdThree);
		
		List<Coach> list = coachDao.getCoachList();
		int listSize = list.size();
		int janeIndex = listSize - 2;
		int jimIndex = listSize - 1;
		
		Assert.assertEquals("Jane", list.get(janeIndex).getFirstName());
		Assert.assertEquals("Jim", list.get(jimIndex).getFirstName());
	}
	
	@Test
	public void testRemoveCoach() {
		Coach coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("John", coach.getFirstName());
		
		coachDao.removeCoach(coachIdOne);
		coach = coachDao.getCoachById(coachIdOne);
		
		Assert.assertEquals(null, coach);
	}
	
	@Test
	public void testUpdateName() {
		Coach coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("John", coach.getFirstName());
		Assert.assertEquals("Doe", coach.getLastName());
		
		coachDao.updateName("Joey", "Jenkins", coachIdOne);
		
		coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("Joey", coach.getFirstName());
		Assert.assertEquals("Jenkins", coach.getLastName());
	}
	
	@Test
	public void testUpdateLocation() {
		Coach coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("update", coach.getCity());
		Assert.assertEquals("update", coach.getState());
		
		coachDao.updateLocation("Columbus", "OH", coachIdOne);
		
		coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("Columbus", coach.getCity());
		Assert.assertEquals("OH", coach.getState());
	}
	
	@Test
	public void updateAboutMe() {
		Coach coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("update", coach.getAboutMe());
		
		coachDao.updateAboutMe("I'm a test coach", coachIdOne);
		
		coach = coachDao.getCoachById(coachIdOne);
		Assert.assertEquals("I'm a test coach", coach.getAboutMe());
	}
}