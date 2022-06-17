package com.manish.sahaj.ticketupgrade.handler;

import java.util.function.Predicate;

import com.manish.sahaj.ticketupgrade.model.FlightTicket;

public class Discount20Processor extends DiscountProcessor {

	public static final String OFFER = "OFFER_20";
	private static final Predicate<FlightTicket> validPredicate = FlightTicket::isValid;
	private static final Predicate<FlightTicket> farePredicate = t -> (t.getFareClass().charAt(0)>='A' && t.getFareClass().charAt(0)<='E');
	
	protected Discount20Processor(DiscountProcessor discountProcessor) {
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
