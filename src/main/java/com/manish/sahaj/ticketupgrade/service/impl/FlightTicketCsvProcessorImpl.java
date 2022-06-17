package com.manish.sahaj.ticketupgrade.service.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.manish.sahaj.ticketupgrade.enums.TicketCSVHeaders;
import com.manish.sahaj.ticketupgrade.exceptions.CustomFileSecurityException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomIOException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomeFileNotFoundException;
import com.manish.sahaj.ticketupgrade.model.FlightTicket;
import com.manish.sahaj.ticketupgrade.model.Ticket;
import com.manish.sahaj.ticketupgrade.service.intf.CSVReadWriteService;
import com.manish.sahaj.ticketupgrade.service.intf.TicketCSVProcessor;

public class FlightTicketCsvProcessorImpl implements TicketCSVProcessor {

	private CSVReadWriteService csvReadWriteService;

	public FlightTicketCsvProcessorImpl(CSVReadWriteService csvReadWriteService) {
		super();
		this.csvReadWriteService = csvReadWriteService;
	}

	@Override
	public List<Ticket> readTicketsFromCSVFile(String filePath) throws CustomeFileNotFoundException, CustomIOException {
		return csvReadWriteService.readData(filePath).stream()
				.map(this::convertToTicketAndUpgrade)
				.collect(Collectors.toList());
	}

	
	@Override
	public void writeUpgradedTicketsToCsvFile(List<Ticket> tickets, String filePath) throws FileNotFoundException, CustomeFileNotFoundException, CustomFileSecurityException {
		
		List<String[]> upgradedTicketRows = new ArrayList<>();
		upgradedTicketRows.add(getUpgradeCSVHeader());
		upgradedTicketRows.addAll(tickets.stream().filter(Ticket::isValid).map(this::convertToCsvArray).collect(Collectors.toList()));
		
		csvReadWriteService.writeData(filePath, upgradedTicketRows);
	}
	
	@Override
	public void writeErrorTicketsToCsvFile(List<Ticket> tickets, String filePath) throws FileNotFoundException, CustomeFileNotFoundException, CustomFileSecurityException {
		
		List<String[]> errorTicketRows = new ArrayList<>();
		errorTicketRows.add(getErrorCSVHeader());
		errorTicketRows.addAll(tickets.stream().filter(t->!t.isValid()).map(this::convertToCsvArray).collect(Collectors.toList()));
		
		csvReadWriteService.writeData(filePath, errorTicketRows);
	}
	
	private FlightTicket convertToTicketAndUpgrade(String[] row) {
		return new FlightTicket.FlightTicketBuilder().email(row[TicketCSVHeaders.EMAIL.getIndex()].trim())
				.mobilePhone(row[TicketCSVHeaders.MOBILE_PHONE.getIndex()].trim())
				.bookedCabin(row[TicketCSVHeaders.BOOKED_CABIN.getIndex()].trim())
				.pnr(row[TicketCSVHeaders.PNR.getIndex()].trim())
				.travelDate(row[TicketCSVHeaders.TRAVEL_DATE.getIndex()].trim())
				.ticketingDate(row[TicketCSVHeaders.TICKETING_DATE.getIndex()].trim())
				.fareClass(row[TicketCSVHeaders.FARE_CLASS.getIndex()].trim())
				.firstName(row[TicketCSVHeaders.FIRST_NAME.getIndex()].trim())
				.lastName(row[TicketCSVHeaders.LAST_NAME.getIndex()].trim())
				.pax(row[TicketCSVHeaders.PAX.getIndex()].trim())
				.build();
	}

	private String[] convertToCsvArray(Ticket ticket) {
		String[] row = new String[11];
		
		row[TicketCSVHeaders.FIRST_NAME.getIndex()]=ticket.getFirstName();
		row[TicketCSVHeaders.LAST_NAME.getIndex()]=ticket.getLastName();
		row[TicketCSVHeaders.PNR.getIndex()]=ticket.getPnr();
		row[TicketCSVHeaders.FARE_CLASS.getIndex()]=ticket.getFareClass();
		row[TicketCSVHeaders.TRAVEL_DATE.getIndex()]=ticket.getTravelDate();
		row[TicketCSVHeaders.PAX.getIndex()]=ticket.getPax();
		row[TicketCSVHeaders.TICKETING_DATE.getIndex()]=ticket.getTicketingDate();
		row[TicketCSVHeaders.EMAIL.getIndex()]=ticket.getEmail();
		row[TicketCSVHeaders.MOBILE_PHONE.getIndex()]=ticket.getMobilePhone();
		row[TicketCSVHeaders.BOOKED_CABIN.getIndex()]=ticket.getBookedCabin();
		if (ticket.isValid())
			row[TicketCSVHeaders.DISCOUNT_CODE.getIndex()]=ticket.getDiscountCode();
		else
			row[TicketCSVHeaders.ERROR.getIndex()]=ticket.getError();
		return row;
	}
	
	private String[] getCSVHeader() {
		String[] row = new String[11];
		
		row[TicketCSVHeaders.FIRST_NAME.getIndex()]= TicketCSVHeaders.FIRST_NAME.getValue();
		row[TicketCSVHeaders.LAST_NAME.getIndex()]= TicketCSVHeaders.LAST_NAME.getValue();
		row[TicketCSVHeaders.PNR.getIndex()]= TicketCSVHeaders.PNR.getValue();
		row[TicketCSVHeaders.FARE_CLASS.getIndex()]= TicketCSVHeaders.FARE_CLASS.getValue();
		row[TicketCSVHeaders.TRAVEL_DATE.getIndex()]= TicketCSVHeaders.TRAVEL_DATE.getValue();
		row[TicketCSVHeaders.PAX.getIndex()]= TicketCSVHeaders.PAX.getValue();
		row[TicketCSVHeaders.TICKETING_DATE.getIndex()]= TicketCSVHeaders.TICKETING_DATE.getValue();
		row[TicketCSVHeaders.EMAIL.getIndex()]= TicketCSVHeaders.EMAIL.getValue();
		row[TicketCSVHeaders.MOBILE_PHONE.getIndex()]= TicketCSVHeaders.MOBILE_PHONE.getValue();
		row[TicketCSVHeaders.BOOKED_CABIN.getIndex()]= TicketCSVHeaders.BOOKED_CABIN.getValue();
		
		return row;
	}

	private String[] getErrorCSVHeader() {
		String[] row = getCSVHeader();
		row[TicketCSVHeaders.ERROR.getIndex()]= TicketCSVHeaders.ERROR.getValue();
		return row;
	}
	
	private String[] getUpgradeCSVHeader() {
		String[] row = getCSVHeader();
		row[TicketCSVHeaders.DISCOUNT_CODE.getIndex()]= TicketCSVHeaders.DISCOUNT_CODE.getValue();
		return row;
	}
}
