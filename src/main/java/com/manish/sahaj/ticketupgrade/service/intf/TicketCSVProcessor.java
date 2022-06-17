package com.manish.sahaj.ticketupgrade.service.intf;

import java.io.FileNotFoundException;
import java.util.List;

import com.manish.sahaj.ticketupgrade.exceptions.CustomFileSecurityException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomIOException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomeFileNotFoundException;
import com.manish.sahaj.ticketupgrade.model.Ticket;

public interface TicketCSVProcessor {

	List<Ticket> readTicketsFromCSVFile(String filePath) throws CustomeFileNotFoundException, CustomIOException;

	void writeUpgradedTicketsToCsvFile(List<Ticket> tickets, String filePath)
			throws FileNotFoundException, CustomeFileNotFoundException, CustomFileSecurityException;

	void writeErrorTicketsToCsvFile(List<Ticket> tickets, String filePath)
			throws FileNotFoundException, CustomeFileNotFoundException, CustomFileSecurityException;
}
