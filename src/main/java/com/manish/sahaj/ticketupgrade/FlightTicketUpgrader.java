package com.manish.sahaj.ticketupgrade;

import com.manish.sahaj.ticketupgrade.handler.DiscountProcessorChain;
import com.manish.sahaj.ticketupgrade.model.ApplicationProperties;
import com.manish.sahaj.ticketupgrade.service.impl.CSVReadWriteServiceImpl;
import com.manish.sahaj.ticketupgrade.service.impl.FlightTicketCsvProcessorImpl;
import com.manish.sahaj.ticketupgrade.service.impl.FlightTicketServiceImpl;
import com.manish.sahaj.ticketupgrade.service.intf.TicketService;

public class FlightTicketUpgrader {

	public static void main(String[] args) {
		
		TicketService ticketService = new FlightTicketServiceImpl(
				new FlightTicketCsvProcessorImpl(
				new CSVReadWriteServiceImpl(ApplicationProperties.CSV_SEPARATOR)));
		
		ticketService.upgradeTickets(ApplicationProperties.CSV_INPUT_PATH, DiscountProcessorChain.getInstance());
	
	}
}
