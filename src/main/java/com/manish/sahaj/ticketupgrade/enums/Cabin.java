package com.manish.sahaj.ticketupgrade.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Cabin {

	ECONOMY("Economy"),
	PREMIUM_ECONOMY("Premium Economy"),
	BUSINESS("Business"),
	FIRST("First");

	private static final Set<String> CABIN_DESCRIPTION_SET = Arrays.asList(Cabin.values()).stream()
			.map(Cabin::getDesription).collect(Collectors.toSet());

	private final String desription;

	private Cabin(String desc) {
		this.desription = desc;
	}

	public String getDesription() {
		return desription;
	}

	public static boolean isValidByDescription(String discription) {
		return CABIN_DESCRIPTION_SET.contains(discription);
	}
}
