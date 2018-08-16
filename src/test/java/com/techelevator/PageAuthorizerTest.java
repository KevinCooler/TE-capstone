package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.Objects.User;
import com.techelevator.security.PageAuthorizer;


public class PageAuthorizerTest {
	
	private PageAuthorizer authorizer;
	private User nullUser;
	private User client;
	private User coach;
	private User admin;
	

	@Before
	public void setup() {
		authorizer = new PageAuthorizer();
		nullUser = null;
		client = new User();
		client.setRole("client");
		coach = new User();
		coach.setRole("coach");
		admin = new User();
		admin.setRole("admin");
	}
	
	@Test
	public void returns_true_when_user_not_admin() {
		Assert.assertTrue(authorizer.isNotAdmin(nullUser));
		Assert.assertTrue(authorizer.isNotAdmin(client));
		Assert.assertTrue(authorizer.isNotAdmin(coach));
		Assert.assertFalse(authorizer.isNotAdmin(admin));
	}
	
}
