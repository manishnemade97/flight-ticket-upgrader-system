package com.manish.sahaj.ticketupgrade.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.manish.sahaj.ticketupgrade.model.FlightTicket;

public class TestDiscountProcessorChain {
	
	DiscountProcessorChain chain = DiscountProcessorChain.getInstance();

	@Test
	public void testDiscountProcessorChainOffer20() {
    	
		FlightTicket ticket = new FlightTicket.FlightTicketBuilder()
				.email("abc@gmail.com")
				.bookedCabin("Economy")
				.fareClass("B")
				.firstName("abc")
				.lastName("xyz")
				.mobilePhone("1234567890")
				.pax("3")
				.pnr("X1Y2Z4")
				.ticketingDate("2019-05-21")
				.travelDate("2019-07-31")
				.build();
		ticket.validate();
		chain.process(ticket);
		assertEquals(Discount20Processor.OFFER, ticket.getDiscountCode());
	}
	
	@Test
	public void testDiscountProcessorChainOffer30() {
    	
		FlightTicket ticket = new FlightTicket.FlightTicketBuilder()
				.email("abc@gmail.com")
				.bookedCabin("Economy")
				.fareClass("F")
				.firstName("abc")
				.lastName("xyz")
				.mobilePhone("1234567890")
				.pax("3")
				.pnr("X1Y2Z4")
				.ticketingDate("2019-05-21")
				.travelDate("2019-07-31")
				.build();
		ticket.validate();
		chain.process(ticket);
		assertEquals(Discount30Processor.OFFER, ticket.getDiscountCode());
	}
	
	@Test
	public void testDiscountProcessorChainOffer25() {
    	
		FlightTicket ticket = new FlightTicket.FlightTicketBuilder()
				.email("abc@gmail.com")
				.bookedCabin("Economy")
				.fareClass("L")
				.firstName("abc")
				.lastName("xyz")
				.mobilePhone("1234567890")
				.pax("3")
				.pnr("X1Y2Z4")
				.ticketingDate("2019-05-21")
				.travelDate("2019-07-31")
				.build();
		ticket.validate();
		chain.process(ticket);
		assertEquals(Discount25Processor.OFFER, ticket.getDiscountCode());
	}
	
	@Test
	public void testDiscountProcessorChainNoOffer() {
    	
		FlightTicket ticket = new FlightTicket.FlightTicketBuilder()
				.email("abc@gmail.com")
				.bookedCabin("Economy")
				.fareClass("U")
				.firstName("abc")
				.lastName("xyz")
				.mobilePhone("1234567890")
				.pax("3")
				.pnr("X1Y2Z4")
				.ticketingDate("2019-05-21")
				.travelDate("2019-07-31")
				.build();
		ticket.validate();
		chain.process(ticket);
		assertEquals(null, ticket.getDiscountCode());
	}
	
	@Test
	public void testDiscountProcessorChainInvalidTicket() {
    	
		FlightTicket ticket = new FlightTicket.FlightTicketBuilder()
				.email("abc@com")
				.bookedCabin("Economy")
				.fareClass("B")
				.firstName("abc")
				.lastName("xyz")
				.mobilePhone("1234567890")
				.pax("3")
				.pnr("X1Y2Z4")
				.ticketingDate("2019-05-21")
				.travelDate("2019-07-31")
				.build();
		ticket.validate();
		chain.process(ticket);
		assertEquals(null, ticket.getDiscountCode());
	}
}
