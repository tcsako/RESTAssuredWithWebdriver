package com.epam.restassured;

//TODO: 1. create basic webdriver script + proper page object pattern usage
//TODO: 2. create basic script to do the subscription with webdriver and verify with rest
//TODO: 3. create composition for subscription page with PO (using abstract factory)
//TODO: 4. cerate CSV reader with Singleton design pattern
//TODO: 5. create DDT script for REST script
//TODO: 6. create DDT script for webdriver script
//TODO: 7. create rest script without BDD style (using JUnit asssertions)

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.pojo.csv.CSVRestTestInput;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BasicServiceTest {
    private static final Logger log = Logger.getLogger(BasicServiceTest.class);
    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";
    private static final int HTTP_OK = HttpStatus.SC_OK;
    // Default file name to read input data
    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_rest.csv";
    // CSV file header
    private static final String[] DEFAULT_FILE_HEADER_MAPPING = {"firstName", "lastName", "emailAddress",
            "emailAddressConfirmation", "newsletterOptIn"};

    private List<String> listToVerifyEmail;
    private CSVRestTestInput testInput;

    @Before
    public void setUp() throws TestExecutionException {

        log.info("*******************************************");
        log.info("Deleting existing records");
        if (given().delete(ServiceTestingProperties.REST_API_URL).getStatusCode() == HTTP_OK) {
            log.info("Records were deleted successfully");
        } else {
            log.info("Something went wrong! Existing records couldn't be deleted");
        }

        log.info("Reading test data from CSV file");
        testInput = CSVReaderUtilitySingleton.getInstance().getIntput(DEFAULT_TEST_INPUT_FILE,
                DEFAULT_FILE_HEADER_MAPPING).get(0);
    }

    @Test
    public void addRecord() throws TestExecutionException {

        log.info("Creating test data container");
        listToVerifyEmail = new ArrayList<String>();

        log.info("Adding test data to test data container");
        listToVerifyEmail.add(testInput.getEmailAddress());

        given().contentType(ServiceTestingProperties.JSON_CONTENT_TYPE).and()
                .post(ServiceTestingProperties.getUrlToPostData(testInput.getFirstName(), testInput.getLastName(),
                        testInput.getEmailAddress(), testInput.getEmailAddressConfirmation(),
                        String.valueOf(testInput.isNewsletterOptIn())));

        when().get(ServiceTestingProperties.REST_API_URL).

                then().statusCode(HTTP_OK).and().content(CONTENT_NUMBER_OF_ELEMENTS, is(NUMBER_OF_RESPONSE)).and()
                .content(CONTENT_EMAIL_ADDRESS, equalTo(listToVerifyEmail));
    }
}
