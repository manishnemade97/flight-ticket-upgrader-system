package com.manish.sahaj.ticketupgrade.model;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.manish.sahaj.ticketupgrade.utils.StringUtil;

public class TestApplicationProperties {

	@Test
	public void testProperties() {
		assertFalse(StringUtil.isEmpty(ApplicationProperties.CSV_ERROR_OUTPUT_PATH));
		assertFalse(StringUtil.isEmpty(ApplicationProperties.CSV_INPUT_PATH));
		assertFalse(StringUtil.isEmpty(ApplicationProperties.CSV_SEPARATOR));
		assertFalse(StringUtil.isEmpty(ApplicationProperties.CSV_UPGRADE_OUTPUT_PATH));
	}
}
