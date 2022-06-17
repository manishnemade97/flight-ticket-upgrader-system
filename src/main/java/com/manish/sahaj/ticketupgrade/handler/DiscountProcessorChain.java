package com.manish.sahaj.ticketupgrade.handler;

import com.manish.sahaj.ticketupgrade.model.FlightTicket;

public class DiscountProcessorChain {
	
	private static final DiscountProcessorChain INSTANCE = new DiscountProcessorChain();

	DiscountProcessor chain;
	 
	private DiscountProcessorChain(){
	    buildChain();
	}
	
	public static DiscountProcessorChain getInstance(){
		return INSTANCE;
	}
	 
	private void buildChain(){
	    chain = new Discount20Processor(new Discount25Processor(new Discount30Processor(null)));
	}
	 
	public void process(FlightTicket request) {
	    chain.process(request);
	}
}
