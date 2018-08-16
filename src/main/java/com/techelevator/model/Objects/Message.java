package com.techelevator.model.Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

	private long id;
	private long senderId;
	private String senderName;
	private long receiverId;
	private String receiverName;
	private String messageText;
	private LocalDateTime createDate;
	private boolean didUserSend;

	public String getCreateDate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");
		return this.createDate.format(format);
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public boolean isDidUserSend() {
		return didUserSend;
	}
	public void setDidUserSend(boolean didUserSend) {
		this.didUserSend = didUserSend;
	}
}