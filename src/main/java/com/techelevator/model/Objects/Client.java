package com.techelevator.model.Objects;

public class Client {
	
	private long id;
	private String firstName;
	private String lastName;
	private boolean isLookingForCoach;
	private String state;
	private String city;
	private String aboutMe;
	private boolean completed = false;
	private Long pairedWith = null;
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Long getPairedWith() {
		return pairedWith;
	}
	public void setPairedWith(long pairedWith) {
		this.pairedWith = pairedWith;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean getIsLookingForCoach() {
		return isLookingForCoach;
	}
	public void setLookingForCoach(boolean isLookingForCoach) {
		this.isLookingForCoach = isLookingForCoach;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
}
