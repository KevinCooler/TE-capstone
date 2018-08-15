package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.Client;
import com.techelevator.model.JDBCClientDAO;
import com.techelevator.model.JDBCUserDAO;
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
		userId = userDAO.saveUser("DAO", "IntegrationTest", "client");
		//clientDAO.addClient(firstName, lastName, id);
	}
	
	@Test
	public void test_client_addition() {
		Client client = clientDAO.getClientById(userId);
		Assert.assertEquals(userId, client.getId());
		Assert.assertEquals("DAO", client.getFirstName());
		Assert.assertEquals("IntegrationTest", client.getLastName());
	}
}
