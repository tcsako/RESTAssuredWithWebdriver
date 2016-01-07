package com.epam.restassured;

//TODO: 1. create basic webdriver script + proper page object pattern usage  - [FINISHED]
//TODO: 2. create basic script to do the subscription with webdriver and verify with rest - [FINISHED]
//TODO: 3. create composition for subscription page with PO (using abstract factory) - [FINISHED]
//TODO: 4. cerate CSV reader with Singleton design pattern - [FINISHED]
//TODO: 5. create DDT script for REST script - [FINISHED]
//TODO: 6. create DDT script for webdriver script
//TODO: 7. create rest script without BDD style (using JUnit asssertions)

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.pageobjects.SignUpPagePageObject;
import com.epam.restassured.pageobjects.ThankYouPagePageObject;
import com.epam.restassured.pageobjects.ThankYouPageVerifier;
import com.epam.restassured.pojo.csv.CSVRestTestInput;
import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Represents an automated subscription to the newsletter with the {@link WebDriver} and REST validation.
 * Test data is read from a .CSV file.
 * <p/>
 * Created by Tamas_Csako
 */
public class BasicServiceTestWithWebDriverAndRest {
    private static final Logger log = Logger.getLogger(BasicServiceTestWithWebDriverAndRest.class);
    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";

    // Test data
    private CSVRestTestInput testInput;
    // Default file name to read input data
    private static final int HTTP_OK = HttpStatus.SC_OK;
    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_rest_and_webdriver.csv";
    // CSV file header
    private static final String[] DEFAULT_FILE_HEADER_MAPPING = {"firstName", "lastName", "emailAddress",
            "emailAddressConfirmation", "newsletterOptIn"};
    // Verification
    private List<String> listToVerifyEmail;

    private SignUpPagePageObject signUpPagePageObject;
    private ThankYouPagePageObject thankYouPagePageObject;
    private ThankYouPageVerifier thankYouPageVerifier;
    private WebDriver driver;

    /**
     * Responsible for setting up test data and test environment.
     *
     * @throws TestExecutionException
     */
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

        log.info("Setting up verification data");
        listToVerifyEmail = new ArrayList<String>();
        listToVerifyEmail.add(testInput.getEmailAddress());

        log.info("Initializing Firefox driver");
        driver = new FirefoxDriver();

        log.info("Opening subscription page");
        driver.get("https://t7-f0x.rhcloud.com/subscription/subscription.html");
        signUpPagePageObject = new SignUpPagePageObject(driver);
    }

    /**
     * Makes an automated subscription then checks the data correctness.
     *
     * @throws TestExecutionException
     */
    @Test
    public void addRecord() throws TestExecutionException {

        log.info("Filling fields and sending data");
        signUpPagePageObject.givenSignUp(testInput.getFirstName(), testInput.getLastName(), testInput.getEmailAddress(),
                testInput.getEmailAddressConfirmation(), Boolean.valueOf((testInput.isNewsletterOptIn())));
        thankYouPagePageObject = new ThankYouPagePageObject(driver);

        log.info("Checking 'Thank you' page URL, subscriber name and e-mail");
        thankYouPageVerifier = new ThankYouPageVerifier(thankYouPagePageObject);
        thankYouPageVerifier.whenSubscribeFinishedCheckDataOnPage(testInput.getFirstName(), testInput.getEmailAddress());

        when().get(ServiceTestingProperties.REST_API_URL).
                then().statusCode(HTTP_OK).
                and().content(CONTENT_NUMBER_OF_ELEMENTS, is(NUMBER_OF_RESPONSE)).
                and().content(CONTENT_EMAIL_ADDRESS, equalTo(listToVerifyEmail));
    }

    /**
     * Closes the browser after test execution.
     */
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
