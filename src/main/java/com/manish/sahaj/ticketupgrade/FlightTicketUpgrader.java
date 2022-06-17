package com.manish.sahaj.ticketupgrade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.manish.sahaj.ticketupgrade.exceptions.CustomFileSecurityException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomIOException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomeFileNotFoundException;
import com.manish.sahaj.ticketupgrade.model.ApplicationProperties;
import com.manish.sahaj.ticketupgrade.model.Ticket;
import com.manish.sahaj.ticketupgrade.service.impl.CSVReadWriteServiceImpl;
import com.manish.sahaj.ticketupgrade.service.impl.FlightTicketCsvProcessorImpl;
import com.manish.sahaj.ticketupgrade.service.intf.TicketCSVProcessor;

public class FlightTicketUpgrader {

	private static final Logger log = Logger.getLogger(FlightTicketUpgrader.class.getName());

	public static void main(String[] args) {
		
		TicketCSVProcessor ticketCSVProcessor = new FlightTicketCsvProcessorImpl(new CSVReadWriteServiceImpl());
		
		List<Ticket> flightTickets = new ArrayList<>();
		log.info("Ticket Csv Processing Started...");
		try {
		
			flightTickets = ticketCSVProcessor.upgradeTicketsFromCSVFile(ApplicationProperties.CSV_INPUT_PATH, ApplicationProperties.CSV_SEPARATOR);
			log.info("Ticket Csv Processing Completed...");
		
		} catch (CustomeFileNotFoundException | CustomIOException e2) {
			log.info("There was an exception while processing csv file.");
			e2.printStackTrace();
		}
		
		try {
			
			ticketCSVProcessor.writeErrorTicketsToCsvFile(flightTickets, ApplicationProperties.CSV_ERROR_OUTPUT_PATH, ApplicationProperties.CSV_SEPARATOR);
			log.info("Error Csv Created...");
		
		} catch (FileNotFoundException | CustomeFileNotFoundException | CustomFileSecurityException e1) {
			log.info("There was an exception while writing Error file.");
			e1.printStackTrace();
		}
		
		try {
			
			ticketCSVProcessor.writeUpgradedTicketsToCsvFile(flightTickets, ApplicationProperties.CSV_UPGRADE_OUTPUT_PATH, ApplicationProperties.CSV_SEPARATOR);
			log.info("Upgrade Csv Created...");

		} catch (FileNotFoundException | CustomeFileNotFoundException | CustomFileSecurityException e) {
			log.info("There was an exception while writing Upgrade file.");
			e.printStackTrace();
		}
		
	}
}
