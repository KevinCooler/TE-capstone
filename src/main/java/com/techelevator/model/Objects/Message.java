package com.techelevator.model.Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

	private long id;
	private long clientId;
	private long coachId;
	private String messageText;
	private LocalDateTime createDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getCreateDate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy H:m");
		return this.createDate.format(format);
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
}