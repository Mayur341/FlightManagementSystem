package com.giczi.david.flight.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "passengers")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@OneToMany(mappedBy = "passenger")
	private List<FlightTicket> flightTickets;
	private String userName;
	private String password;
	private boolean admin;
	
	public Passenger() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<FlightTicket> getFlightTickets() {
		return flightTickets;
	}

	public void setFlightTickets(List<FlightTicket> flightTicets) {
		this.flightTickets = flightTicets;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Passenger [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", userName=" + userName + ", password=" + password + ", admin="
				+ admin + "]";
	}
	

}
