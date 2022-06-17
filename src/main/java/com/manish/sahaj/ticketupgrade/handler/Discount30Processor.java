package com.manish.sahaj.ticketupgrade.handler;

import java.util.function.Predicate;

import com.manish.sahaj.ticketupgrade.model.FlightTicket;

public class Discount30Processor extends DiscountProcessor {
	public static final String OFFER = "OFFER_30";
	private static final Predicate<FlightTicket> validPredicate = FlightTicket::isValid;
	private static final Predicate<FlightTicket> farePredicate = t -> (t.getFareClass().charAt(0)>='F' && t.getFareClass().charAt(0)<='K');
	
	protected Discount30Processor(DiscountProcessor discountProcessor) {
		super(discountProcessor);
	}

	@Override
    public void process(FlightTicket ticket)
    {
		if(validPredicate.test(ticket) && farePredicate.test(ticket))
			ticket.setDiscountCode(OFFER);
		else
			super.process(ticket);
    }

}
