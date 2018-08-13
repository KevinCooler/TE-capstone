package com.techelevator.model;

import java.util.List;

public class Coach {

	private long id;
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private String aboutMe;
	private List<Availability> available;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public List<Availability> getAvailable() {
		return available;
	}
	public void setAvailable(List<Availability> available) {
		this.available = available;
	}
}