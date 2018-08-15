package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCAvailabilityDAO;
import com.techelevator.model.JDBCDAOs.JDBCCoachDAO;
import com.techelevator.model.Objects.Availability;


public class JDBCAvailabilityDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCAvailabilityDAO availDao;
	private JDBCCoachDAO coachDao;
	
	@Before
	public void setup() {
		availDao = new JDBCAvailabilityDAO(super.getDataSource());
		coachDao = new JDBCCoachDAO(super.getDataSource());
		coachDao.addCoach("John", "Doe", 1);
	}
	
	@Test
	public void testAddingAvailability() {
		availDao.addAvailability(1, 1, 12, 14);
		availDao.addAvailability(1, 1, 14, 16);
		availDao.addAvailability(1, 1, 16, 18);
		
		List<Availability> list = availDao.getAvailabilityList(1);
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testListWithNoAvailability() {
		List<Availability> list = availDao.getAvailabilityList(1);
		
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void testCorrectOrder() {
		availDao.addAvailability(1, 3, 12, 14);
		availDao.addAvailability(1, 2, 16, 17);
		availDao.addAvailability(1, 2, 8, 10);
		
		List<Availability> list = availDao.getAvailabilityList(1);
		
		Assert.assertEquals(8, list.get(0).getHourStart());
		Assert.assertEquals(16, list.get(1).getHourStart());
		Assert.assertEquals(12, list.get(2).getHourStart());
	}
	
	@Test
	public void testAvailabilityContents() {
		availDao.addAvailability(1, 4, 8, 12);
		List<Availability> list = availDao.getAvailabilityList(1);
		
		Assert.assertEquals(4, list.get(0).getDay());
		Assert.assertEquals(8, list.get(0).getHourStart());
		Assert.assertEquals(12, list.get(0).getHourEnd());
	}
	
	@Test
	public void testRemoveAvailability() {
		availDao.addAvailability(1, 1, 12, 14);
		availDao.addAvailability(1, 1, 14, 16);
		availDao.addAvailability(1, 1, 16, 18);
		List<Availability> list = availDao.getAvailabilityList(1);
		
		Assert.assertEquals(3, list.size());
		
		availDao.removeAvailability(list.get(0).getId());
		list = availDao.getAvailabilityList(1);
		Assert.assertEquals(2, list.size());
		
		availDao.removeAvailability(list.get(0).getId());
		list = availDao.getAvailabilityList(1);
		Assert.assertEquals(1, list.size());
		
		availDao.removeAvailability(list.get(0).getId());
		list = availDao.getAvailabilityList(1);
		Assert.assertEquals(0, list.size());
	}
}