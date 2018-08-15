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
		//userId = userDAO.saveUser("DAO", "IntegrationTest", "client");
		//clientDAO.addClient(firstName, lastName, id);
	}
	
	@Test
	public void test_client() {
		Client client = clientDAO.getClientById(7);
		Assert.assertEquals(7, client.getId());
		Assert.assertEquals("Trey", client.getFirstName());
		Assert.assertEquals("Tomlin", client.getLastName());
	}
}
