package com.giczi.david.flight.domain;

import java.util.Date;


public class FlightData {
	
	private Passenger passenger;	
	private String departurePlace;
	private String arrivalPlace;
	private Date departureDate;
	private Date arrivalDate;
	private int debit;
	private boolean paid;
	
	public FlightData(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(String arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "FlightData [passenger=" + passenger + ", departurePlace=" + departurePlace + ", arrivalPlace="
				+ arrivalPlace + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate + ", debit="
				+ debit + ", paid=" + paid + "]";
	}
	
}
