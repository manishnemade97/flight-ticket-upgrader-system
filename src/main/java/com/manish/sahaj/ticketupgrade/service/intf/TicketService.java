package com.manish.sahaj.ticketupgrade.service.intf;

import java.util.List;

import com.manish.sahaj.ticketupgrade.handler.DiscountProcessorChain;
import com.manish.sahaj.ticketupgrade.model.Ticket;

public interface TicketService {

	void upgradeTickets(String inputPath, DiscountProcessorChain processorChain);

	void upgradeTickets(List<Ticket> flightTickets, DiscountProcessorChain processorChain);
}
