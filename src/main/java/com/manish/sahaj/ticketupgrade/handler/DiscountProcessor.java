package com.manish.sahaj.ticketupgrade.handler;

import com.manish.sahaj.ticketupgrade.model.FlightTicket;

public abstract class DiscountProcessor {

	DiscountProcessor next;
	
	protected DiscountProcessor(DiscountProcessor discountProcessor) {
		this.next = discountProcessor;
	}
	
    public void process(FlightTicket ticket){
        if(next != null) 
        	next.process(ticket);
    }
}
