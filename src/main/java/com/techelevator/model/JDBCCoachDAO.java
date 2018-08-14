package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCCoachDAO implements CoachDAO{
	
	private JdbcTemplate temp;
	
	@Autowired
	public JDBCCoachDAO(DataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}

	@Override
	public Coach getCoachById(long id) {
		String sqlStatement = "SELECT * FROM coaches WHERE coach_id=?;";
		SqlRowSet result = temp.queryForRowSet(sqlStatement, id);
		
		if(result.next())
			return mapRowToCoach(result);
		
		return null;
	}
	
	@Override
	public List<Coach> getCoachList() {
		List<Coach> list = new ArrayList<Coach>();
		String sqlStatement = "SELECT * FROM coaches;";
		SqlRowSet results = temp.queryForRowSet(sqlStatement);
		
		while(results.next())
			list.add(mapRowToCoach(results));
		
		return list;
	}

	private Coach mapRowToCoach(SqlRowSet result) {
		Coach coach = new Coach();
		
		coach.setId(result.getLong("coach_id"));
		coach.setFirstName(result.getString("first_name"));
		coach.setLastName(result.getString("last_name"));
		coach.setCity(result.getString("city_location"));
		coach.setState(result.getString("state_location"));
		coach.setAboutMe(result.getString("about_me"));
		
		return coach;
	}

	@Override
	public void addCoach(String firstName, String lastName, long id) {
		String sqlStatement = "INSERT INTO coaches (coach_id, first_name, "
				+ "last_name, city_location, state_location, about_me) "
				+ "VALUES(?, ?, ?, 'update', 'update', 'update');";
		
		temp.update(sqlStatement, id, firstName, lastName);
	}

	@Override
	public void removeCoach(long id) {
		String SqlStatement = "DELETE FROM coaches WHERE coach_id=?;";
		
		temp.update(SqlStatement, id);
	}

	@Override
	public void updateName(String firstName, String lastName, long id) {
		String sqlStatement = "UPDATE coaches SET first_name=?, last_name=? WHERE coach_id=?;";
		
		temp.update(sqlStatement, firstName, lastName, id);
	}

	@Override
	public void updateLocation(String city, String state, long id) {
		String sqlStatement = "UPDATE coaches SET city_location=?, state_location=? WHERE coach_id=?;";
		
		temp.update(sqlStatement, city, state, id);
	}

	@Override
	public void updateAboutMe(String aboutMe, long id) {
		String sqlStatement = "UPDATE coaches SET about_me=? WHERE coach_id=?;";
		
		temp.update(sqlStatement, aboutMe, id);
	}

	@Override
	public boolean addCoachAvailability(int day, int hour) {
		// TODO Auto-generated method stub
		return false;
	}
}