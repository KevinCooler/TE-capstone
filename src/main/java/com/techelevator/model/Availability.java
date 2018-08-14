package com.techelevator.model;

public class Availability {
	private long id;
	private long coachId;
	private int day;
	private int hourStart;
	private int hourEnd;
	
	public int getHourStart() {
		return hourStart;
	}
	public void setHourStart(int hourStart) {
		this.hourStart = hourStart;
	}
	public int getHourEnd() {
		return hourEnd;
	}
	public void setHourEnd(int hourEnd) {
		this.hourEnd = hourEnd;
	}
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}