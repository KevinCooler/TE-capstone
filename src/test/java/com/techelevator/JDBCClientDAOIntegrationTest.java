package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCClientDAO;
import com.techelevator.model.Objects.Client;

public class JDBCClientDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCClientDAO clientDAO;
	private long clientIdOne;
	private long clientIdTwo;
	private long clientIdThree;
	TestingUtilities util;
	
	@Before
	public void setup() {
		util = new TestingUtilities(super.getDataSource());
		clientDAO = new JDBCClientDAO(super.getDataSource());

		clientIdOne = util.newUser("test1");
		clientDAO.addClient("Test", "Guy", clientIdOne);
	}
	
	@Test
	public void test_client() {
		Client client = clientDAO.getClientById(clientIdOne);
		Assert.assertEquals(clientIdOne, client.getId());
		Assert.assertEquals("Test", client.getFirstName());
		Assert.assertEquals("Guy", client.getLastName());
	}
	
	@Test
	public void test_remove_coach() {
		Client client = clientDAO.getClientById(clientIdOne);
		Assert.assertEquals("Test", client.getFirstName());
		
		clientDAO.removeClient(clientIdOne);
		client = clientDAO.getClientById(clientIdOne);
		
		Assert.assertEquals(null, client);
	}
	
	@Test
	public void test_update_name() {
		Client client = clientDAO.getClientById(clientIdOne);
		Assert.assertEquals("Test", client.getFirstName());
		Assert.assertEquals("Guy", client.getLastName());
		
		clientDAO.updateName("new", "name", clientIdOne);
		client = clientDAO.getClientById(clientIdOne);
		
		Assert.assertEquals("new", client.getFirstName());
		Assert.assertEquals("name", client.getLastName());
	}
	
	@Test
	public void testUpdateLocation() {
		Client client = clientDAO.getClientById(clientIdOne);
		Assert.assertEquals("update", client.getCity());
		Assert.assertEquals("update", client.getState());
		
		clientDAO.updateLocation("Columbus", "OH", clientIdOne);
		
		client = clientDAO.getClientById(clientIdOne);
		Assert.assertEquals("Columbus", client.getCity());
		Assert.assertEquals("OH", client.getState());
	}
	
	@Test
	public void updateAboutMe() {
		Client client = clientDAO.getClientById(clientIdOne);
		Assert.assertEquals("update", client.getAboutMe());
		
		clientDAO.updateAboutMe("I'm a test client", clientIdOne);
		
		client = clientDAO.getClientById(clientIdOne);
		Assert.assertEquals("I'm a test client", client.getAboutMe());
	}
	
	@Test
	public void testClientList() {
		int size = clientDAO.getClientList().size();
		
		clientIdTwo = util.newUser("test2");
		clientIdThree = util.newUser("test3");
		clientDAO.addClient("Test", "Girl", clientIdTwo);
		clientDAO.addClient("Test", "Person", clientIdThree);
		
		Assert.assertEquals(size + 2, clientDAO.getClientList().size());
	}
	
	@Test
	public void testAssignCoach() {
		long coachId = util.newCoach("Test", "Coach");
		
		clientDAO.assignCoach(clientIdOne, coachId);
		Client client = clientDAO.getClientById(clientIdOne);
		
		Assert.assertEquals((Long)coachId, client.getPairedWith());
	}
	
	@Test
	public void testGettingClientsByCoach() {
		clientIdTwo = util.newUser("test2");
		clientDAO.addClient("Test", "Girl", clientIdTwo);
		clientIdThree = util.newUser("test3");
		clientDAO.addClient("Test", "Person", clientIdThree);
		
		long coachId = util.newCoach("Test", "Coach");
		
		clientDAO.assignCoach(clientIdOne, coachId);
		clientDAO.assignCoach(clientIdTwo, coachId);
		clientDAO.assignCoach(clientIdThree, coachId);
		
		Assert.assertEquals(3, clientDAO.getClientsByCoach(coachId).size());
	}
	
	@Test
	public void testUpdatingLookingForCoach() {
		Client client = clientDAO.getClientById(clientIdOne);
		Assert.assertTrue(client.getIsLookingForCoach());
		
		clientDAO.updateIsLookingForCoach(false, clientIdOne);
		client = clientDAO.getClientById(clientIdOne);
		Assert.assertFalse(client.getIsLookingForCoach());
		
		clientDAO.updateIsLookingForCoach(true, clientIdOne);
		client = clientDAO.getClientById(clientIdOne);
		Assert.assertTrue(client.getIsLookingForCoach());
	}
	
	@Test
	public void testUpdatingCompleted() {
		Client client = clientDAO.getClientById(clientIdOne);
		Assert.assertFalse(client.isCompleted());
		
		clientDAO.updateCompleted(true, clientIdOne);
		client = clientDAO.getClientById(clientIdOne);
		Assert.assertTrue(client.isCompleted());
	}
}