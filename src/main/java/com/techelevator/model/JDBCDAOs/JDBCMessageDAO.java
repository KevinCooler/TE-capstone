package com.techelevator.model.JDBCDAOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.DAOs.MessageDAO;
import com.techelevator.model.Objects.Message;

public class JDBCMessageDAO implements MessageDAO{
	
	private JdbcTemplate temp;
	
	@Autowired
	public JDBCMessageDAO(DataSource dataSource) {
		temp = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Message> getMessages(long coachId) {
		List<Message> list = new ArrayList<Message>();
		String sqlStatement = "SELECT * FROM messages WHERE coach_id=?;";
		SqlRowSet results = temp.queryForRowSet(sqlStatement, coachId);
		
		while(results.next())
			list.add(mapRowToMessage(results));
			
		return list;
	}

	private Message mapRowToMessage(SqlRowSet results) {
		Message message = new Message();
		
		message.setId(results.getLong("id"));
		message.setClientId(results.getLong("client_id"));
		message.setCoachId(results.getLong("coach_id"));
		message.setMessageText(results.getString("message_test"));
		LocalDateTime time = results.getTimestamp("create_date").toLocalDateTime();
		message.setCreateDate(time);
		
		return message;
	}

	@Override
	public Message viewMessage(long messageId) {
		String sqlStatement = "SELECT * FROM messages WHERE id=?;";
		SqlRowSet result = temp.queryForRowSet(sqlStatement, messageId);
		
		if(result.next())
			return mapRowToMessage(result);
		
		return null;
	}

	@Override
	public void addMessage(long clientId, long coachId, String messageText) {
		String sqlStatement = "INSERT INTO messages "
				+ "(client_id, coach_id, message_test, create_date) "
				+ "VALUES(?, ?, ?, NOW());";
		
		temp.update(sqlStatement, clientId, coachId, messageText);
	}

	@Override
	public void removeMessage(long messageId) {
		String sqlStatement = "DELETE FROM messages WHERE id=?;";
		
		temp.update(sqlStatement, messageId);
	}
}