package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.JDBCDAOs.JDBCUserDAO;
import com.techelevator.model.Objects.User;
import com.techelevator.security.PasswordHasher;


public class JDBCUserDAOIntegrationTest extends DAOIntegrationTest{
	
	private JDBCUserDAO userDAO;
	private PasswordHasher hashMaster;
	
	@Before
	public void setup() {
		hashMaster = new PasswordHasher();
		this.userDAO = new JDBCUserDAO(getDataSource(), hashMaster);
	}
	
	@Test
	public void saves_user_and_returns_user_by_username() {
		userDAO.saveUser("testUserName",  "testPassword", "testRole");
		User user = userDAO.getUserByUserName("testUserName");
		Assert.assertEquals("testRole",  user.getRole());
	}
	
	@Test
	public void saves_user_and_verifies_that_username_matches_with_password() {
		userDAO.saveUser("testUserName",  "testPassword", "testRole");
		Assert.assertTrue(userDAO.searchForUsernameAndPassword("testUserName",  "testPassword"));
	}
	
	@Test
	public void returns_false_with_incorrect_password_search() {
		userDAO.saveUser("testUserName",  "testPassword", "testRole");
		Assert.assertFalse(userDAO.searchForUsernameAndPassword("testUserName",  "wrongPassword"));
	}
	
	@Test
	public void old_password_fails_after_updated() {
		userDAO.saveUser("testUserName",  "originalPassword", "testRole");
		userDAO.updatePassword("testUserName",  "updatedPassword");
		Assert.assertFalse(userDAO.searchForUsernameAndPassword("testUserName",  "originalPassword"));
	}
	
	@Test
	public void updated_password_succeeds_after_updated() {
		userDAO.saveUser("testUserName",  "originalPassword", "testRole");
		userDAO.updatePassword("testUserName",  "updatedPassword");
		Assert.assertTrue(userDAO.searchForUsernameAndPassword("testUserName",  "updatedPassword"));
	}
	
	@Test
	public void returns_false_when_search_for_deleted_user() {
		userDAO.saveUser("testUserName",  "testPassword", "testRole");
		User user = userDAO.getUserByUserName("testUserName");
		userDAO.deleteUserByUserId(user.getId());
		Assert.assertFalse(userDAO.searchForUsernameAndPassword(user.getUserName(),  user.getPassword()));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
