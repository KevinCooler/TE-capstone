package com.techelevator.model.JDBCDAOs;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.Objects.User;
import com.techelevator.security.PasswordHasher;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}
	
	@Override
	public Long saveUser(String userName, String password, String role) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		String sqlInsertUser = "INSERT INTO app_user(user_name, password, role, salt) VALUES (?, ?, ?, ?) returning id";
		return jdbcTemplate.queryForObject(sqlInsertUser, Long.class,
				userName, hashedPassword, role, saltString);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(user_name) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		jdbcTemplate.update("UPDATE app_user SET password = ? WHERE user_name = ?", hashedPassword, userName);
		jdbcTemplate.update("UPDATE app_user SET salt = ? WHERE user_name = ?", saltString, userName);
	}

	@Override
	public User getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM app_user "+
		"WHERE UPPER(user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase()); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setRole(user.getString("role"));
			thisUser.setId(user.getLong("id"));
		}

		return thisUser;
	}
	
	@Override
	public User getUserByUserId(long userId) {
		String sqlSearchForUsername ="SELECT * "+
			"FROM app_user "+
			"WHERE id = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userId); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setRole(user.getString("role"));
			thisUser.setId(user.getLong("id"));
		}

		return thisUser;
	}

	@Override
	public void deleteUserByUserId(Long userId) {
		String sqlDeleteUser = "Delete from app_user where id = ?";
		jdbcTemplate.update(sqlDeleteUser, userId);
		
	}

}
