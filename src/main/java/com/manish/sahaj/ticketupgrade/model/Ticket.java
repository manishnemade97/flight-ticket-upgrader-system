package com.manish.sahaj.ticketupgrade.model;

import com.manish.sahaj.ticketupgrade.handler.DiscountProcessorChain;

public abstract class Ticket {

	private String firstName;
	private String lastName;
	private String email;
	private String mobilePhone;
	private String pnr;
	private String fareClass;
	private String travelDate;
	private String ticketingDate;
	private String pax;
	private String bookedCabin;
	
	private String discountCode;
	private String error;
	private boolean valid;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public void setTicketingDate(String ticketingDate) {
		this.ticketingDate = ticketingDate;
	}

	public void setPax(String pax) {
		this.pax = pax;
	}

	public void setBookedCabin(String bookedCabin) {
		this.bookedCabin = bookedCabin;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getPnr() {
		return pnr;
	}

	public String getFareClass() {
		return fareClass;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public String getTicketingDate() {
		return ticketingDate;
	}

	public String getPax() {
		return pax;
	}

	public String getBookedCabin() {
		return bookedCabin;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public String getError() {
		return error;
	}

	public boolean isValid() {
		return valid;
	}
	
	public abstract void validate();
	
	public abstract void applyDiscount(DiscountProcessorChain processorChain);
}
