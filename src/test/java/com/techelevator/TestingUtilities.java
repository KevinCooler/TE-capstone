package com.techelevator;

import javax.sql.DataSource;

import com.techelevator.model.JDBCDAOs.JDBCClientDAO;
import com.techelevator.model.JDBCDAOs.JDBCCoachDAO;
import com.techelevator.model.JDBCDAOs.JDBCUserDAO;
import com.techelevator.security.PasswordHasher;

public class TestingUtilities {

	private JDBCCoachDAO coachDao;
	private JDBCUserDAO userDao;
	private JDBCClientDAO clientDao;
	private PasswordHasher hashMaster = new PasswordHasher();;
	
	public TestingUtilities(DataSource dataSource) {
		coachDao = new JDBCCoachDAO(dataSource);
		userDao = new JDBCUserDAO(dataSource, hashMaster);
		clientDao = new JDBCClientDAO(dataSource);
	}
	
	public long newUser(String username) {
		return userDao.saveUser(username, "test", "coach");
	}
	
	public long newCoach(String firstName, String lastName) {
		long coachId = newUser(firstName + lastName);
		
		coachDao.addCoach(firstName, lastName, coachId);
		
		return coachId;
	}
	
	public long newClient(String firstName, String lastName) {
		long clientId = newUser(firstName + lastName);
		
		clientDao.addClient(firstName, lastName, clientId);
		
		return clientId;
	}
}