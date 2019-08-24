package com.Sproject.finalProject.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Integer userId;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column(unique = true, nullable = false)
	private String userName;

	@Column
	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private Set<AddressModel> addressModel;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private Set<EventModel> eventModel;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private Set<BookingModel> bookingModel;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Set<AddressModel> getAddressModel() {
		return addressModel;
	}

	public void setAddressModel(Set<AddressModel> addressModel) {
		this.addressModel = addressModel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<EventModel> getEventModel() {
		return eventModel;
	}

	public void setEventModel(Set<EventModel> eventModel) {
		this.eventModel = eventModel;
	}

	public Set<BookingModel> getBookingModel() {
		return bookingModel;
	}

	public void setBookingModel(Set<BookingModel> bookingModel) {
		this.bookingModel = bookingModel;
	}
	
}
