package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCAvailabilityDAO;
import com.techelevator.model.Objects.Availability;


public class JDBCAvailabilityDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCAvailabilityDAO availDao;
	private TestingUtilities util;
	private long coachId;
	
	@Before
	public void setup() {
		availDao = new JDBCAvailabilityDAO(super.getDataSource());
		util = new TestingUtilities(super.getDataSource());
		
		coachId = util.newCoach("Test", "Coach");
	}
	
	@Test
	public void testAddAvailability() {
		availDao.addAvailability(coachId, 3, 8, 10);
		List<Availability> list = availDao.getAvailabilityList(coachId);
		
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testAvailabilityContents() {
		availDao.addAvailability(coachId, 3, 8, 10);
		Availability avail = availDao.getAvailabilityList(coachId).get(0);
		
		Assert.assertEquals(3, avail.getDay());
		Assert.assertEquals(8, avail.getHourStart());
		Assert.assertEquals(10, avail.getHourEnd());
	}
	
	@Test
	public void testAvailabilityList() {
		availDao.addAvailability(coachId, 3, 8, 10);
		availDao.addAvailability(coachId, 3, 10, 12);
		availDao.addAvailability(coachId, 3, 12, 14);
		
		List<Availability> list = availDao.getAvailabilityList(coachId);
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testRemoveAvailability() {
		long availId = availDao.addAvailability(coachId, 3, 8, 10);
		List<Availability> list = availDao.getAvailabilityList(coachId);
		Assert.assertEquals(1, list.size());
		
		availDao.removeAvailability(availId);
		list = availDao.getAvailabilityList(coachId);
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void testRemovingAvailabilityByCoach() {
		availDao.addAvailability(coachId, 3, 8, 10);
		availDao.addAvailability(coachId, 3, 10, 12);
		availDao.addAvailability(coachId, 3, 12, 14);
		List<Availability> list = availDao.getAvailabilityList(coachId);
		Assert.assertEquals(3, list.size());
		
		availDao.removeAvailabilityByCoachId(coachId);
		list = availDao.getAvailabilityList(coachId);
		Assert.assertEquals(0, list.size());
	}
}