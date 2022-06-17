package com.manish.sahaj.ticketupgrade.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.manish.sahaj.ticketupgrade.constants.Constants;
import com.manish.sahaj.ticketupgrade.constants.ErrorMessageConstants;
import com.manish.sahaj.ticketupgrade.constants.PatternConstants;
import com.manish.sahaj.ticketupgrade.enums.Cabin;
import com.manish.sahaj.ticketupgrade.handler.DiscountProcessorChain;
import com.manish.sahaj.ticketupgrade.utils.StringUtil;

public class FlightTicket extends Ticket {
	
	public FlightTicket(FlightTicketBuilder flightTicketBuilder) {
		this.setFirstName(flightTicketBuilder.firstName);
		this.setLastName(flightTicketBuilder.lastName);
		this.setEmail(flightTicketBuilder.email);
		this.setMobilePhone(flightTicketBuilder.mobilePhone);
		this.setPnr(flightTicketBuilder.pnr);
		this.setFareClass(flightTicketBuilder.fareClass);
		this.setBookedCabin(flightTicketBuilder.bookedCabin);
		this.setPax(flightTicketBuilder.pax);
		this.setTravelDate(flightTicketBuilder.travelDate);
		this.setTicketingDate(flightTicketBuilder.ticketingDate);
	}

	public void validate() {
		boolean validFlag = true;
		StringBuilder errorBuilder = new StringBuilder();
		
		if(StringUtil.isEmpty(getEmail()) || !StringUtil.isValidString(getEmail(), PatternConstants.EMAIL_PATTERN)) {
			validFlag=false;
			errorBuilder.append(ErrorMessageConstants.EMAIL_INVALID).append("-");
		}

		if(StringUtil.isEmpty(getMobilePhone()) || !StringUtil.isValidString(getMobilePhone(), PatternConstants.MOBILE_NUMBER_PATTERN)) {
			validFlag=false;
			errorBuilder.append(ErrorMessageConstants.MOBILE_INVALID).append("-");
		}

		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		try {
			Date ticketingDateDate = sdf.parse(getTicketingDate());
			Date travelDateDate = sdf.parse(getTravelDate());
			if (ticketingDateDate==null || travelDateDate==null || travelDateDate.before(ticketingDateDate)) {
				validFlag=false;
				errorBuilder.append(ErrorMessageConstants.TICKET_DATE_INVALID).append("-");
			}
		} catch (ParseException e) {
			errorBuilder.append(ErrorMessageConstants.DATE_FORMAT_INVALID).append("-");
		}

		if(StringUtil.isEmpty(getPnr()) || !StringUtil.isValidString(getPnr(), PatternConstants.PNR_PATTERN)) {
			validFlag=false;
			errorBuilder.append(ErrorMessageConstants.PNR_INVALID).append("-");
		}
		
		if (StringUtil.isEmpty(getBookedCabin()) || !Cabin.isValidByDescription(getBookedCabin())) {
			validFlag=false;
			errorBuilder.append(ErrorMessageConstants.CABIN_INVALID).append("-");
		}
		
		if (StringUtil.isEmpty(getFareClass()) || !StringUtil.isValidString(getFareClass(), PatternConstants.FARE_CLASS_PATTERN)) {
			validFlag=false;
			errorBuilder.append(ErrorMessageConstants.FARE_CLASS_INVALID).append("-");
		}
		
		this.setValid(validFlag);
		this.setError(errorBuilder.toString());
	}
	
	public static class FlightTicketBuilder {
		
		private String firstName;
		private String lastName;
		private String email;
		private String mobilePhone;
		private String pnr;
		private String fareClass;
		private String bookedCabin;
		private String pax;
		private String travelDate;
		private String ticketingDate;
		
		public FlightTicketBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		public FlightTicketBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		public FlightTicketBuilder email(String email) {
			this.email = email;
			return this;
		}
		public FlightTicketBuilder bookedCabin(String bookedCabin) {
			this.bookedCabin = bookedCabin;
			return this;
		}
		public FlightTicketBuilder pnr(String pnr) {
			this.pnr = pnr;
			return this;
		}
		public FlightTicketBuilder travelDate(String travelDate) {
			this.travelDate = travelDate;
			return this;
		}
		public FlightTicketBuilder ticketingDate(String ticketingDate) {
			this.ticketingDate = ticketingDate;
			return this;
		}
		public FlightTicketBuilder mobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
			return this;
		}
		
		
		public FlightTicketBuilder fareClass(String fareClass) {
			this.fareClass = fareClass;
			return this;
		}
		public FlightTicketBuilder pax(String pax) {
			this.pax = pax;
			return this;
		}
	
		public FlightTicket build() {
			FlightTicket ticket =  new FlightTicket(this);
			ticket.validate();
			ticket.applyDiscount();
			return ticket;
		}
	}

	public void applyDiscount() {
		new DiscountProcessorChain().process(this);
	}
}
