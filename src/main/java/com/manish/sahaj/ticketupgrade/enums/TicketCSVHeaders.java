package com.manish.sahaj.ticketupgrade.enums;

public enum TicketCSVHeaders {
	FIRST_NAME("First_name",0),
	LAST_NAME("Last_name",1),
	PNR("PNR",2),
	FARE_CLASS("Fare_class",3),
	TRAVEL_DATE("Travel_date",4),
	PAX("Pax",5),
	TICKETING_DATE("Ticketing_date",6),
	EMAIL("Email",7),
	MOBILE_PHONE("Mobile_phone",8),
	BOOKED_CABIN("Booked_cabin",9),
	DISCOUNT_CODE("Discount_code",10),
	ERROR("Error",10);

	private final String value;
	
	private final int index;

	TicketCSVHeaders(String value, int index){
		this.index=index;
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
	public int getIndex() {
		return index;
	}
}
