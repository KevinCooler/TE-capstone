package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.JDBCDAOs.JDBCClientDAO;
import com.techelevator.model.JDBCDAOs.JDBCUserDAO;
import com.techelevator.model.Objects.Client;
import com.techelevator.security.PasswordHasher;




public class JDBCClientDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCClientDAO clientDAO;
	private JDBCUserDAO userDAO;
	private long userId;
	private PasswordHasher hashMaster;
	
	@Before
	public void setup() {
		clientDAO = new JDBCClientDAO(super.getDataSource());
		hashMaster = new PasswordHasher();
		userDAO = new JDBCUserDAO(super.getDataSource(), hashMaster);

		userId = userDAO.saveUser("DAO", "password", "client");
		clientDAO.addClient("DAO", "IntegrationTest", userId);

	}
	
	@Test
	public void test_client() {
		Client client = clientDAO.getClientById(7);
		Assert.assertEquals(7, client.getId());
		Assert.assertEquals("Trey", client.getFirstName());
		Assert.assertEquals("Tomlin", client.getLastName());
	}
	@Test
	public void test_remove_coach() {
		Client client = clientDAO.getClientById(userId);
		Assert.assertEquals("DAO", client.getFirstName());
		
		clientDAO.removeClient(userId);
		client = clientDAO.getClientById(userId);
		
		Assert.assertEquals(null, client);
	}
	@Test
	public void test_update_name() {
		Client client = clientDAO.getClientById(userId);
		Assert.assertEquals("DAO", client.getFirstName());
		Assert.assertEquals("IntegrationTest", client.getLastName());
		
		clientDAO.updateName("new", "name", userId);
		client = clientDAO.getClientById(userId);
		
		Assert.assertEquals("new", client.getFirstName());
		Assert.assertEquals("name", client.getLastName());
	}
	@Test
	public void testUpdateLocation() {
		Client client = clientDAO.getClientById(userId);
		Assert.assertEquals("update", client.getCity());
		Assert.assertEquals("update", client.getState());
		
		clientDAO.updateLocation("Columbus", "OH", userId);
		
		client = clientDAO.getClientById(userId);
		Assert.assertEquals("Columbus", client.getCity());
		Assert.assertEquals("OH", client.getState());
	}
	@Test
	public void updateAboutMe() {
		Client client = clientDAO.getClientById(userId);
		Assert.assertEquals("update", client.getAboutMe());
		
		clientDAO.updateAboutMe("I'm a test client", userId);
		
		client = clientDAO.getClientById(userId);
		Assert.assertEquals("I'm a test client", client.getAboutMe());
	}
}
