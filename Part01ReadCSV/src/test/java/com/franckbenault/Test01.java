package com.franckbenault;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

public class Test01 {

	@Test
	public void readCVStest() throws IOException {
		
		Reader in = new FileReader("./src/test/resources/names.csv");
		Iterable<CSVRecord> records = CSVFormat.EXCEL.builder()
				.setHeader()
				  .setSkipHeaderRecord(true)
				  .setIgnoreEmptyLines(true)
				  .build()
				  .parse(in);

		for (CSVRecord record : records) {
			System.out.println(record);
		    String lastName = record.get("Last Name");
		    String firstName = record.get("First Name");
		    System.out.println(lastName);
		    System.out.println(firstName);
		}
	}

	
}

