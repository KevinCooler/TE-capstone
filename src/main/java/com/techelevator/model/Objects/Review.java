package com.techelevator.model.Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {

	private long id;
	private long coachId;
	private long clientId;
	private int rating;
	private String reviewText;
	private LocalDateTime createDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public String getCreateDate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");
		return this.createDate.format(format);
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
}