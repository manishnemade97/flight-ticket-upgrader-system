package com.manish.sahaj.ticketupgrade.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
	
	private ApplicationProperties() {}
	
	static {
		
		Properties p=new Properties();  
		
		try(FileReader reader = new FileReader("src/main/resources/application.properties");) {
			
			p.load(reader);  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CSV_INPUT_PATH = p.getProperty("input-path");
		CSV_ERROR_OUTPUT_PATH = p.getProperty("error-output-path");
		CSV_UPGRADE_OUTPUT_PATH = p.getProperty("upgrade-output-path");
		CSV_SEPARATOR = p.getProperty("csv-separator");
	}

	public static final String CSV_SEPARATOR;
	
	public static final String CSV_INPUT_PATH;
	
	public static final String CSV_ERROR_OUTPUT_PATH;
	
	public static final String CSV_UPGRADE_OUTPUT_PATH;
}
