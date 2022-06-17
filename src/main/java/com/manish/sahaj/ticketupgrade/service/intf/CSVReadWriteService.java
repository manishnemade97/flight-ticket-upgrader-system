package com.manish.sahaj.ticketupgrade.service.intf;

import java.io.FileNotFoundException;
import java.util.List;

import com.manish.sahaj.ticketupgrade.exceptions.CustomFileSecurityException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomIOException;
import com.manish.sahaj.ticketupgrade.exceptions.CustomeFileNotFoundException;

public interface CSVReadWriteService {
	
	List<String[]> readData(String filePath, String separator) throws CustomeFileNotFoundException, CustomIOException;
	
	void writeData(String path, String separator, List<String[]> data) throws FileNotFoundException, CustomeFileNotFoundException, CustomFileSecurityException;
}
