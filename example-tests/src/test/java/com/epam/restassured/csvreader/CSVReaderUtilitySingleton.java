package com.epam.restassured.csvreader;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.pojo.csv.CSVRestTestInput;

/**
 * Singleton representation of a CSV Reader utility class.
 *
 * @author Tamas_Csako
 */
public final class CSVReaderUtilitySingleton {
    private static final Logger LOG = Logger.getLogger(CSVReaderUtilitySingleton.class);

    // Private instance
    private static CSVReaderUtilitySingleton instance;

    // Protected constructor for the Singleton implementation
    private CSVReaderUtilitySingleton() {
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
     * <p>
     * Please note, CSV input files should follow the following format:
     * line, data should start in the first line in the following oder
     * FirstName,LastName,EmailAddress,EmailAddressConfirmation,NewsletterOptIn
     * Use '#' to comment lines
     *
     * @param fileName - file name and path to read input data
     * @return list of input data in CSVTestInput format
     * @throws TestExecutionException exception to handle issues during reading
     */
    public List<CSVRestTestInput> getIntput(String fileName, List<String> fileHeaderMapping) throws TestExecutionException {
        try {
            requireNonNull(fileName);
            requireNonNull(fileHeaderMapping);
            LOG.info("Reading test data from file");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withCommentMarker('#')
                    .withHeader(fileHeaderMapping.toArray(new String[fileHeaderMapping.size()]))
                    .parse(new FileReader(fileName));
            return streamOf(records).map(record -> convertInput(record, fileHeaderMapping)).collect(toList());
        } catch (IOException e) {
            throw new TestExecutionException("Failed to read test input", e);
        }
    }

    private Stream<CSVRecord> streamOf(Iterable<CSVRecord> records) {
        return StreamSupport.stream(records.spliterator(), false);
    }

    /**
     * Private method to convert REST service test data into object
     *
     * @param record one line from CSV file
     * @return converted test input data in CSVRestTestInput pojo
     */
    private CSVRestTestInput convertInput(CSVRecord record, List<String> fileHeaderMapping) {
        return new CSVRestTestInput.CSVRestTestInputBuilder()
                .firstName(record.get(fileHeaderMapping.get(0)))
                .lastName(record.get(fileHeaderMapping.get(1)))
                .emailAddress(record.get(fileHeaderMapping.get(2)))
                .emailAddressConfirmation(record.get(fileHeaderMapping.get(3)))
                .newsletterOptIn(Boolean.valueOf(record.get(fileHeaderMapping.get(4)))).build();
    }
}
