package com.epam.restassured.csvreader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.restassured.pojo.csv.CSVTestInput;
import com.opencsv.CSVReader;

/**
 * Singleton representation of a CSV Reader utility class.
 * 
 * Please note, CSV input files should follow the following format:
 * No comment line, data should start in the first line in the following oder
 * FirstName,LastName,EmailAddress,EmailAddressConfirmation,NewsletterOptIn
 * 
 * Open issue for OpenCSV to add comment line in the first line of the input CSV file
 * 
 * @author Tamas_Csako
 *
 */
public class CSVReaderUtilitySingleton {
	
	//Default file name to read input data
	private static final String DEFAULT_TEST_INPUT_FILE = "test_data_rest.csv";
	//Private instance
	private static CSVReaderUtilitySingleton instance = null;
	
	//Protected constructor for the Singleton implementation
	protected CSVReaderUtilitySingleton(){
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
	 * Method to get test input data from resource file.
	 * 
	 * @param fileName - file name and path to read input data
	 * @return list of input data in CSVTestInput format
	 */
	@SuppressWarnings("resource")
	public List<CSVTestInput> getIntput(String fileName) {
		List <String[]> allLines = null;
		
		//Set default test input data if necessary (parameter is null)
		if (fileName == null) {
			fileName = DEFAULT_TEST_INPUT_FILE;
		}
		
		
		//Read all input data from resource file
		try {
			CSVReader reader = new CSVReader(new FileReader(fileName));
			allLines = reader.readAll();
		} catch (FileNotFoundException e) {
			//Handle exception when file is not found
			//TODO: use Log4j for logging
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			//Handle IO exception during reading from file
			//TODO: use Log4j for logging
			e.printStackTrace();
			return null;
		}
		
		return convertInput(allLines);
	}
	
	/**
	 * Private method to convert text data into objects
	 * 
	 * @param testInputLines String array with test input lines
	 * @return converted test input data in ArrayList
	 */
	private List<CSVTestInput> convertInput(List <String[]> testInputLines) {
		List<CSVTestInput> convertedInput = new ArrayList<CSVTestInput>();
		
		for (String[] line : testInputLines) {
			CSVTestInput input = new CSVTestInput();
			input.setFirstName(line[0]);
			input.setLastName(line[1]);
			input.setEmailAddress(line[2]);
			input.setEmailAddressConfirmation(line[3]);
			input.setNewsletterOptIn(Boolean.valueOf(line[4]));
			convertedInput.add(input);
		}
		
		return convertedInput;
	}
}
