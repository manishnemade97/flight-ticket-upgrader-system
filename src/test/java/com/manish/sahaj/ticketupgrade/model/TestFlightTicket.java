package com.manish.sahaj.ticketupgrade.model;

import org.junit.Before;
import org.junit.Test;

import com.manish.sahaj.ticketupgrade.handler.DiscountProcessorChain;
import com.manish.sahaj.ticketupgrade.utils.StringUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestFlightTicket {
	
	private FlightTicket validFlightTicket;
	private FlightTicket invalidFlightTicket;
	
	@Before
	public void initValid() {
		validFlightTicket = new FlightTicket.FlightTicketBuilder()
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
		validFlightTicket.validate();
	}
	
	@Before
	public void initInvalid() {
		invalidFlightTicket = new FlightTicket.FlightTicketBuilder()
		.email("pqr@com")
		.bookedCabin("Economy1")
		.fareClass("B1")
		.firstName("abc")
		.lastName("xyz")
		.mobilePhone("123456790")
		.pax("3")
		.pnr("X1Y2Z42")
		.ticketingDate("2019-21-05")
		.travelDate("2019-07-31")
		.build();
		invalidFlightTicket.validate();
	}

	@Test
	public void testTicketCreate() {
		assertEquals(validFlightTicket.getEmail(), "abc@gmail.com");
		assertEquals(validFlightTicket.getBookedCabin(), "Economy");
		assertEquals(validFlightTicket.getFareClass(), "B");
		assertEquals(validFlightTicket.getFirstName(), "abc");
		assertEquals(validFlightTicket.getLastName(), "xyz");
		assertEquals(validFlightTicket.getMobilePhone(), "1234567890");
		assertEquals(validFlightTicket.getPax(), "3");
		assertEquals(validFlightTicket.getPnr(), "X1Y2Z4");
		assertEquals(validFlightTicket.getTicketingDate(), "2019-05-21");
		assertEquals(validFlightTicket.getTravelDate(), "2019-07-31");
	}
	
	@Test
	public void testTicketValid() {
		assertEquals(true, validFlightTicket.isValid());
		assertTrue(StringUtil.isEmpty(validFlightTicket.getError()));
	}
	
	@Test
	public void testDiscountApplied() {
		validFlightTicket.applyDiscount(DiscountProcessorChain.getInstance());
		assertFalse(StringUtil.isEmpty(validFlightTicket.getDiscountCode()));
		assertEquals("OFFER_20",validFlightTicket.getDiscountCode());
		
		assertTrue(StringUtil.isEmpty(invalidFlightTicket.getDiscountCode()));
	}
	
	@Test
	public void testTicketInvalid() {
		assertEquals(false, invalidFlightTicket.isValid());
		assertFalse(StringUtil.isEmpty(invalidFlightTicket.getError()));
	}
	
}
