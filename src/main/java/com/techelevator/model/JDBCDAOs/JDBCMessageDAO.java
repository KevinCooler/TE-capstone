package com.techelevator.model.JDBCDAOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DAOs.MessageDAO;
import com.techelevator.model.Objects.Message;

@Component
public class JDBCMessageDAO implements MessageDAO{
	
	private JdbcTemplate temp;
	
	@Autowired
	public JDBCMessageDAO(DataSource dataSource) {
		temp = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Message> getMessages(long userId) {
		List<Message> list = new ArrayList<Message>();
		String sqlStatement = "SELECT * FROM messages "
				+ "WHERE sender_id=? OR receiver_id=? "
				+ "ORDER BY create_date DESC;";
		
		SqlRowSet results = temp.queryForRowSet(sqlStatement, userId, userId);
		
		while(results.next())
			list.add(mapRowToMessage(results, userId));
			
		return list;
	}

	private Message mapRowToMessage(SqlRowSet results, long userId) {
		Message message = new Message();
		
		message.setId(results.getLong("id"));
		message.setSenderId(results.getLong("sender_id"));
		message.setSenderName(results.getString("sender_name"));
		message.setReceiverId(results.getLong("receiver_id"));
		message.setReceiverName(results.getString("receiver_name"));
		message.setMessageText(results.getString("message_text"));
		LocalDateTime time = results.getTimestamp("create_date").toLocalDateTime();
		message.setCreateDate(time);
		message.setDidUserSend(results.getLong("sender_id") == userId);
		
		return message;
	}

	@Override
	public Message viewMessage(long messageId, long userId) {
		String sqlStatement = "SELECT * FROM messages WHERE id=?;";
		SqlRowSet result = temp.queryForRowSet(sqlStatement, messageId);
		
		if(result.next())
			return mapRowToMessage(result, userId);
		
		return null;
	}

	@Override
	public long addMessage(long senderId, String senderName,
			long receiverId, String receiverName, String messageText) {
		String sqlStatement = "INSERT INTO messages "
				+ "(sender_id, sender_name, receiver_id, "
				+ "receiver_name, message_text, create_date) "
				+ "VALUES(?, ?, ?, ?, ?, NOW()) returning id;";
		
		return temp.queryForObject(sqlStatement, Long.class, senderId, senderName, receiverId, receiverName, messageText);
	}

	@Override
	public void removeMessage(long messageId) {
		String sqlStatement = "DELETE FROM messages WHERE id=?;";
		
		temp.update(sqlStatement, messageId);
	}

	@Override
	public void removeMessagesByUserId(long userId) {
		String sqlStatement = "DELETE FROM messages WHERE sender_id = ? OR receiver_id = ?;";
		
		temp.update(sqlStatement, userId, userId);
	}
}