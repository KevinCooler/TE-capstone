package com.techelevator.model.JDBCDAOs;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DAOs.AvailabilityDAO;
import com.techelevator.model.Objects.Availability;

@Component
public class JDBCAvailabilityDAO implements AvailabilityDAO{
	
	JdbcTemplate temp;
	
	@Autowired
	public JDBCAvailabilityDAO(DataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Availability> getAvailabilityList(long coachId) {
		List<Availability> list = new ArrayList<Availability>();
		String sqlStatement = "SELECT * FROM availability WHERE coach_id=? "
				+ "ORDER BY day_of_week, hour_start, hour_end;";
		
		SqlRowSet results = temp.queryForRowSet(sqlStatement, coachId);
		
		while(results.next())
			list.add(mapRowToAvailability(results));
		
		return list;
	}

	private Availability mapRowToAvailability(SqlRowSet results) {
		Availability avail = new Availability();
		
		avail.setId(results.getLong("availability_id"));
		avail.setCoachId(results.getLong("coach_id"));
		avail.setDay(results.getInt("day_of_week"));
		avail.setHourStart(results.getInt("hour_start"));
		avail.setHourEnd(results.getInt("hour_end"));
		
		return avail;
	}

	@Override
	public long addAvailability(long coachId, int day, int hourStart, int hourEnd) {
		String sqlStatement = "INSERT INTO availability "
				+ "(coach_id, day_of_week, hour_start, hour_end) "
				+ "VALUES (?, ?, ?, ?) returning availability_id;";
		
		return temp.queryForObject(sqlStatement, Long.class, coachId, day, hourStart, hourEnd);
	}

	@Override
	public void removeAvailability(long id) {
		String sqlStatement = "DELETE FROM availability WHERE availability_id=?;";
		
		temp.update(sqlStatement, id);
	}
	
	@Override
	public void removeAvailabilityByCoachId(long coachId) {
		String sqlStatement = "DELETE FROM availability WHERE coach_id=?;";
		
		temp.update(sqlStatement, coachId);
	}
}