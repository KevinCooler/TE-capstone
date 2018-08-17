package com.techelevator.model.JDBCDAOs;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DAOs.FeedbackDAO;
import com.techelevator.model.Objects.Feedback;

@Component
public class JDBCFeedbackDAO implements FeedbackDAO{
	
	private JdbcTemplate template;
	
	@Autowired
	public JDBCFeedbackDAO(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Feedback> getFeedbackByClientId(long clientId) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		String sqlSelectFeedbackByClient = "SELECT * FROM feedback WHERE client_id = ? ORDER BY module DESC";
		SqlRowSet result = template.queryForRowSet(sqlSelectFeedbackByClient, clientId);
		while(result.next()) {
			feedbacks.add(mapRowToFeedback(result));
		}
		return feedbacks;
	}

	@Override
	public long addFeedback(long clientId, int module, String detail) {
		String sqlInsertFeedback = "insert into feedback (client_id, module, detail) values (?, ?, ?) returning id;";
		return template.queryForObject(sqlInsertFeedback, Long.class, clientId, module, detail);
	}

	@Override
	public void removeFeedback(long feedbackId) {
		String sqlDeleteFeedback = "delete from feedback where id = ?;";
		template.update(sqlDeleteFeedback, feedbackId);
	}

	@Override
	public void updateFeedback(long feedbackId, String detail) {
		String sqlUpdateFeedback = "update feedback set detail = ? where id = ?;";
		template.update(sqlUpdateFeedback, detail, feedbackId);
	}
	
	@Override
	public Feedback getFeedbackById(long feedbackId) {
		Feedback feedback = null;
		String sqlSelectFeedbackById = "SELECT * FROM feedback WHERE id = ?";
		SqlRowSet result = template.queryForRowSet(sqlSelectFeedbackById, feedbackId);
		if(result.next()) {
			feedback = mapRowToFeedback(result);
		}
		return feedback;
	}
	
	private Feedback mapRowToFeedback(SqlRowSet result) {
		Feedback feedback = new Feedback();
		
		feedback.setId(result.getLong("id"));
		feedback.setClientId(result.getLong("client_id"));
		feedback.setModule(result.getInt("module"));
		feedback.setDetail(result.getString("detail"));
		
		return feedback;
	}

	@Override
	public void createClientFeedback(long clientId) {
		String sqlStatement = "INSERT INTO feedback (client_id, module, detail) VALUES (?, ?, ?);";
		for(int i = 1; i < 13; i++) {
		template.update(sqlStatement, clientId, i, "");
		}
	}
}