package com.manish.sahaj.ticketupgrade.service.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.manish.sahaj.ticketupgrade.exceptions.CustomFileSecurityException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomIOException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomeFileNotFoundException;
import com.manish.sahaj.ticketupgrade.handler.DiscountProcessorChain;
import com.manish.sahaj.ticketupgrade.model.ApplicationProperties;
import com.manish.sahaj.ticketupgrade.model.Ticket;
import com.manish.sahaj.ticketupgrade.service.intf.TicketCSVProcessor;
import com.manish.sahaj.ticketupgrade.service.intf.TicketService;

public class FlightTicketServiceImpl implements TicketService {

	private static final Logger log = Logger.getLogger(FlightTicketServiceImpl.class.getName());
	
	private TicketCSVProcessor ticketCSVProcessor;
	
	public FlightTicketServiceImpl(TicketCSVProcessor ticketCSVProcessor) {
		super();
		this.ticketCSVProcessor = ticketCSVProcessor;
	}

	@Override
	public void upgradeTickets(List<Ticket> flightTickets, DiscountProcessorChain processorChain) {
		// Validate tickets
		flightTickets.forEach(Ticket::validate);

		// Upgrade tickets
		flightTickets.forEach(t -> t.applyDiscount(processorChain));
	}

	@Override
	public void upgradeTickets(String inputPath, DiscountProcessorChain processorChain) {

		List<Ticket> flightTickets = new ArrayList<>();
		log.info("Ticket Csv Processing Started...");
		
		try {
			
			// Read tickets from CSV
			flightTickets = ticketCSVProcessor.readTicketsFromCSVFile(inputPath);
			log.info("Ticket Csv Processing Completed...");

			upgradeTickets(flightTickets, processorChain);

			// Create Error CSV
			ticketCSVProcessor.writeErrorTicketsToCsvFile(flightTickets, ApplicationProperties.CSV_ERROR_OUTPUT_PATH);
			log.info("Error Csv Created...");

			// Create Valid Upgraded CSV
			ticketCSVProcessor.writeUpgradedTicketsToCsvFile(flightTickets,
					ApplicationProperties.CSV_UPGRADE_OUTPUT_PATH);
			log.info("Upgrade Csv Created...");
		
		} catch (FileNotFoundException | CustomeFileNotFoundException | CustomFileSecurityException
				| CustomIOException e) {
			e.printStackTrace();
		}
	}

}
