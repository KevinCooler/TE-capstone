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
	private User pageUser;
	long pageUserId;

	@Before
	public void setup() {
		authorizer = new PageAuthorizer();
		nullUser = null;
		client = new User();
		client.setRole("client");
		client.setId(222);
		coach = new User();
		coach.setId(333);
		coach.setRole("coach");
		admin = new User();
		admin.setId(444);
		admin.setRole("admin");
		pageUserId = 111;
		pageUser = new User();
		pageUser.setId(pageUserId);
	}
	
	@Test
	public void returns_true_when_user_not_admin() {
		Assert.assertTrue(authorizer.isNotAdmin(nullUser));
		Assert.assertTrue(authorizer.isNotAdmin(client));
		Assert.assertTrue(authorizer.isNotAdmin(coach));
		Assert.assertFalse(authorizer.isNotAdmin(admin));
	}
	
	@Test
	public void returns_true_when_user_not_coach() {
		Assert.assertTrue(authorizer.isNotCoach(nullUser));
		Assert.assertTrue(authorizer.isNotCoach(client));
		Assert.assertFalse(authorizer.isNotCoach(coach));
		Assert.assertTrue(authorizer.isNotCoach(admin));
	}
	
	@Test
	public void returns_true_when_user_not_client() {
		Assert.assertTrue(authorizer.isNotClient(nullUser));
		Assert.assertFalse(authorizer.isNotClient(client));
		Assert.assertTrue(authorizer.isNotClient(coach));
		Assert.assertTrue(authorizer.isNotClient(admin));
	}
	
	@Test
	public void returns_true_when_not_the_page_use_id() {
		Assert.assertTrue(authorizer.isNotThisUser(nullUser, pageUserId));
		Assert.assertTrue(authorizer.isNotThisUser(client, pageUserId));
		Assert.assertTrue(authorizer.isNotThisUser(coach, pageUserId));
		Assert.assertTrue(authorizer.isNotThisUser(admin, pageUserId));
		Assert.assertFalse(authorizer.isNotThisUser(pageUser, pageUserId));
	}
	
	@Test
	public void returns_true_when_user_not_coach_or_admin() {
		Assert.assertTrue(authorizer.isNotAdmin(nullUser) && authorizer.isNotCoach(nullUser));
		Assert.assertTrue(authorizer.isNotAdmin(client) && authorizer.isNotCoach(client));
		Assert.assertFalse(authorizer.isNotAdmin(coach) && authorizer.isNotCoach(coach));
		Assert.assertFalse(authorizer.isNotAdmin(admin) && authorizer.isNotCoach(admin));
	}
	
}
