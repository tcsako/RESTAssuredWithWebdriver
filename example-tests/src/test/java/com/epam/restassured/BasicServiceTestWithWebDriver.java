package com.epam.restassured;

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.pageobjects.SignUpPagePageObject;
import com.epam.restassured.pageobjects.SignUpPageVerifier;
import com.epam.restassured.pageobjects.ThankYouPagePageObject;
import com.epam.restassured.pageobjects.ThankYouPageVerifier;
import com.epam.restassured.pojo.csv.CSVRestTestInput;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.jayway.restassured.RestAssured.given;

/**
 * Represents an automated subscription to the newsletter with the {@link WebDriver}.
 * Test data is read from a .CSV file.
 * 
 * Created by Peter_Olah1 on 12/16/2015.
 */
public class BasicServiceTestWithWebDriver {

    private Logger log = Logger.getLogger(BasicServiceTestWithWebDriver.class);
    
    private CSVRestTestInput testInput;
	// Default file name to read input data
    private static final int HTTP_OK = HttpStatus.SC_OK;
	private static final String DEFAULT_TEST_INPUT_FILE = "test_data_webdriver.csv";
	// CSV file header
	private static final String[] DEFAULT_FILE_HEADER_MAPPING = { "firstName", "lastName", "emailAddress",
			"emailAddressConfirmation", "newsletterOptIn" };
	
	private String baseURL;
	private WebDriver driver;
    private SignUpPagePageObject signUpPagePageObject;
    private ThankYouPageVerifier thankYouPageVerifier;
    private SignUpPageVerifier signUpPageVerifier;
    private ThankYouPagePageObject thankYouPage;

    /**
     * Sets up test data and creates page object instances.
     *
     */
    @Before
    public void setUp() throws TestExecutionException  {

    	 log.info("Deleting existing records");
         if (given().delete(ServiceTestingProperties.REST_API_URL).getStatusCode() == HTTP_OK) {
             log.info("Records were deleted successfully");
         } else {
             log.info("Something went wrong! Existing records couldn't be deleted");
         }

        log.info("Reading test data from CSV file");
		testInput = CSVReaderUtilitySingleton.getInstance().getIntput(DEFAULT_TEST_INPUT_FILE,
				DEFAULT_FILE_HEADER_MAPPING).get(0);

		baseURL = "https://t7-f0x.rhcloud.com/subscription/subscription.html";
		
        log.info("Setting up verification data");
        log.info("Initializing Firefox driver");
        driver = new FirefoxDriver();
        log.info("Opening subscription page");
        driver.get(baseURL);
        signUpPagePageObject = new SignUpPagePageObject(driver);
        signUpPageVerifier = new SignUpPageVerifier(signUpPagePageObject);
    }

    /**
     *  Signs up with the given data. Makes basic verifications about the page display and elements behavior.
     *
     */
    @Test
    public void signUpSubscriber() throws TestExecutionException  {
        driver.get(baseURL);
        signUpPageVerifier.checkSignUpPageFields();
        signUpPageVerifier.checkSignUpPageHeaders();
        signUpPagePageObject.givenSignUp(testInput.getFirstName(), testInput.getLastName(), testInput.getEmailAddress(), testInput.getEmailAddressConfirmation(), Boolean.valueOf((testInput.isNewsletterOptIn())));
        thankYouPage = new ThankYouPagePageObject(driver);
        thankYouPageVerifier = new ThankYouPageVerifier(thankYouPage);
        thankYouPageVerifier.whenSubscribeFinishedCheckDataOnPage(testInput.getFirstName(), testInput.getEmailAddress());
    }

    /**
     * Closes the browser after test execution.
     */
    @After
    public void tearDown() {
        log.info("Closing browser");
        if (driver != null) {
            driver.quit();
        }
    }
}


