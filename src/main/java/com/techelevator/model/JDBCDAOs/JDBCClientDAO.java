package com.techelevator.model.JDBCDAOs;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DAOs.ClientDAO;
import com.techelevator.model.Objects.Client;
@Component
public class JDBCClientDAO implements ClientDAO {
	
	private JdbcTemplate temp;
	
	@Autowired
	public JDBCClientDAO(DataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}

	@Override
	public Client getClientById(long id) {
		String sqlStatement = "SELECT * FROM clients WHERE client_id=?;";
		SqlRowSet result = temp.queryForRowSet(sqlStatement, id);
		
		if(result.next())
			return mapRowToClient(result);
		
		return null;
	}

	@Override
	public List<Client> getClientList() {
		List<Client> clients = new ArrayList<Client>();
		String sqlStatement = "SELECT * FROM clients "
				+ "ORDER BY is_looking_for_coach DESC, last_name;";
		SqlRowSet result = temp.queryForRowSet(sqlStatement);
		
		while(result.next())
			clients.add(mapRowToClient(result));
		
		return clients;
	}
	
	public List<Client> getClientsByCoach(long coachId) {
		List<Client> clients = new ArrayList<Client>();
		String sqlStatement = "SELECT * FROM clients WHERE paired_with = ? "
				+ "ORDER BY completed DESC, last_name;";
		SqlRowSet results = temp.queryForRowSet(sqlStatement, coachId);
		
		while(results.next())
			clients.add(mapRowToClient(results));

		return clients;
	}

	@Override
	public void addClient(String firstName, String lastName, long id) {
		String sqlStatement = "INSERT INTO clients (client_id, first_name, "
				+ "last_name, city_location, state_location, about_me, completed, is_looking_for_coach) "
				+ "VALUES(?, ?, ?, 'update', 'update', 'update', 'false', 'true');";
		
		temp.update(sqlStatement, id, firstName, lastName);	
	}

	@Override
	public void removeClient(long id) {
		String SqlStatement = "DELETE FROM clients WHERE client_id=?;";
		
		temp.update(SqlStatement, id);
	}

	@Override
	public void updateName(String firstName, String lastName, long id) {
		String sqlStatement = "UPDATE clients SET first_name=?, last_name=? WHERE client_id=?;";
		
		temp.update(sqlStatement, firstName, lastName, id);
	}

	@Override
	public void updateLocation(String city, String state, long id) {
		String sqlStatement = "UPDATE clients SET city_location=?, state_location=? WHERE client_id=?;";
		
		temp.update(sqlStatement, city, state, id);
		
	}

	@Override
	public void updateAboutMe(String aboutMe, long id) {
		String sqlStatement = "UPDATE clients SET about_me=? WHERE client_id=?;";
		
		temp.update(sqlStatement, aboutMe, id);
	}
	
	private Client mapRowToClient(SqlRowSet result) {
		Client client = new Client();
		client.setId(result.getLong("client_id"));
		client.setFirstName(result.getString("first_name"));
		client.setLastName(result.getString("last_name"));
		client.setState(result.getString("state_location"));
		client.setCity(result.getString("city_location"));
		client.setLookingForCoach(result.getBoolean("is_looking_for_coach"));
		client.setAboutMe(result.getString("about_me"));
		client.setPairedWith(result.getLong("paired_with"));
		client.setCompleted(result.getBoolean("completed"));
		
		return client;
	}

	@Override
	public void updateIsLookingForCoach(boolean isLookingForCoach, long clientId) {
		String sqlStatement = "UPDATE clients SET is_looking_for_coach = ? WHERE client_id = ?;";
		
		temp.update(sqlStatement, isLookingForCoach, clientId);
	}

	@Override
	public void assignCoach(long clientId, Long coachId) {
		String sqlStatement = "UPDATE clients SET paired_with = ? "
				+ "WHERE client_id = ?;";
		
		temp.update(sqlStatement, coachId, clientId);
	}
	
	@Override
	public void unassignCoachById(long coachId) {
		String sqlStatement = "UPDATE clients SET paired_with = null "
				+ "WHERE paired_with = ?;";
		
		temp.update(sqlStatement, coachId);
	}	

	@Override
	public void updateCompleted(boolean completed, long clientId) {
		String sqlStatement = "UPDATE clients SET completed = ? WHERE client_id = ?;";
		
		temp.update(sqlStatement, completed, clientId);
	}
}
