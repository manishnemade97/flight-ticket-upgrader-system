package com.manish.sahaj.ticketupgrade.handler;

import com.manish.sahaj.ticketupgrade.model.FlightTicket;

public class DiscountProcessorChain {

	DiscountProcessor chain;
	 
	public DiscountProcessorChain(){
	    buildChain();
	}
	 
	private void buildChain(){
	    chain = new Discount20Processor(new Discount25Processor(new Discount30Processor(null)));
	}
	 
	public void process(FlightTicket request) {
	    chain.process(request);
	}
}
