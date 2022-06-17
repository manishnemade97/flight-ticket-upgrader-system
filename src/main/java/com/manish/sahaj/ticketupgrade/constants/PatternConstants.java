package com.manish.sahaj.ticketupgrade.constants;

import java.util.regex.Pattern;

public class PatternConstants {

	private PatternConstants() {}
	
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$");
	public static final Pattern PNR_PATTERN = Pattern.compile("^[a-zA-Z0-9]{6}$");
	public static final Pattern FARE_CLASS_PATTERN = Pattern.compile("^[A-Z]{1}$");
}
