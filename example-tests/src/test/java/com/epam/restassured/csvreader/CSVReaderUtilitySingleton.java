package com.epam.restassured.csvreader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.pojo.csv.CSVRestTestInput;

/**
 * Singleton representation of a CSV Reader utility class.
 * 
 * @author Tamas_Csako
 *
 */
public class CSVReaderUtilitySingleton {
	// Private instance
	private static CSVReaderUtilitySingleton instance = null;

	// Protected constructor for the Singleton implementation
	protected CSVReaderUtilitySingleton() {
	}

	/**
	 * Public method to get instance for the Singleton class
	 * 
	 * @return instance of the Singleton object
	 */
	public static CSVReaderUtilitySingleton getInstance() {

		if (instance == null) {
			instance = new CSVReaderUtilitySingleton();
		}

		return instance;
	}

	/**
	 * Method to get test input data from resource file for REST service related
	 * scripts. 
	 * 
	 * Please note, CSV input files should follow the following format:
	 * line, data should start in the first line in the following oder
	 * FirstName,LastName,EmailAddress,EmailAddressConfirmation,NewsletterOptIn
	 * Use '#' to comment lines
	 * 
	 * 
	 * @param fileName
	 *            - file name and path to read input data
	 * @return list of input data in CSVTestInput format
	 * @throws TestExecutionException
	 *             exception to handle issues during reading
	 */
	public List<CSVRestTestInput> getIntput(String fileName, String[] fileHeaderMapping) throws TestExecutionException {
		List<CSVRestTestInput> testInputs = null;
		FileReader fileReader = null;

		// Read all input data from resource file
		try {
			// Create a new list of test input to be filled by CSV file data
			testInputs = new ArrayList<CSVRestTestInput>();

			// initialize FileReader object
			fileReader = new FileReader(fileName);

			// Get formatted CSV input
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withCommentMarker('#').withHeader(fileHeaderMapping)
					.parse(fileReader);

			// Read the CSV file records starting from the second record to skip
			// the header
			for (CSVRecord record : records) {
				testInputs.add(convertInput(record, fileHeaderMapping));
			}
		} catch (IOException e) {
			// Handle exception when file is not found
			// TODO: use Log4j for logging
			throw new TestExecutionException(e.getMessage());
		}

		return testInputs;
	}

	/**
	 * Private method to convert REST service test data into object
	 * 
	 * @param record
	 *            one line from CSV file
	 * @return converted test input data in CSVRestTestInput pojo
	 */
	private CSVRestTestInput convertInput(CSVRecord record, String[] fileHeaderMapping) {
		return new CSVRestTestInput.CSVRestTestInputBuilder().firstName(record.get(fileHeaderMapping[0]))
				.lastName(record.get(fileHeaderMapping[1])).emailAddress(record.get(fileHeaderMapping[2]))
				.emailAddressConfirmation(record.get(fileHeaderMapping[3]))
				.newsletterOptIn(Boolean.valueOf(record.get(fileHeaderMapping[4]))).build();
	}
}
