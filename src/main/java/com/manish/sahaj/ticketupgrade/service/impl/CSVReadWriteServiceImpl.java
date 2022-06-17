package com.manish.sahaj.ticketupgrade.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.manish.sahaj.ticketupgrade.exceptions.CustomFileSecurityException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomIOException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomeFileNotFoundException;
import com.manish.sahaj.ticketupgrade.service.intf.CSVReadWriteService;

public class CSVReadWriteServiceImpl implements CSVReadWriteService {

	private final String csvSeparator;
	
	public CSVReadWriteServiceImpl(String separator) {
		super();
		csvSeparator = separator;
	}

	@Override
	public List<String[]> readData(String filePath) throws CustomeFileNotFoundException, CustomIOException {

		String line = "";

		List<String[]> data = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			line = br.readLine();//to skip header
			while ((line = br.readLine()) != null)
				data.add(line.split(csvSeparator));

		} catch (FileNotFoundException e) {
			throw new CustomeFileNotFoundException("File not found at "+ filePath, e);
		} catch (IOException e) {
			throw new CustomIOException("Issue occured while reading file "+ filePath, e);
		}
		
		return data;
	}
	
	@Override
	public void writeData(String path, List<String[]> data) throws CustomeFileNotFoundException, CustomFileSecurityException {

		File csvOutputFile = new File(path);
	    
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	    	data.stream()
	          .map(d->String.join(csvSeparator,d))
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
	    	throw new CustomeFileNotFoundException("File not found at "+ path, e);
		} catch (SecurityException e) {
			throw new CustomFileSecurityException("Issue occured while accessing file "+path, e);
		}
	}

}
