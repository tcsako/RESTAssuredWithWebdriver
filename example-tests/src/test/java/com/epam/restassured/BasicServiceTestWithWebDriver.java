package com.epam.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.pageobjects.SignUpPagePageObject;
import com.epam.restassured.pageobjects.SignUpPageVerifier;
import com.epam.restassured.pageobjects.ThankYouPagePageObject;
import com.epam.restassured.pageobjects.ThankYouPageVerifier;
import com.epam.restassured.pojo.csv.CSVRestTestInput;
import com.google.common.collect.ImmutableList;

/**
 * Represents an automated subscription to the newsletter with the {@link WebDriver}.
 * Test data is read from a .CSV file.
 * <p>
 * Created by Peter_Olah1 on 12/16/2015.
 */
public class BasicServiceTestWithWebDriver {
    private static final Logger LOG = Logger.getLogger(BasicServiceTestWithWebDriver.class);

    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_webdriver.csv";
    private static final List<String> DEFAULT_TEST_PARAMETERS = ImmutableList.of("firstName", "lastName", "emailAddress", "emailAddressConfirmation", "newsletterOptIn");

    private WebDriver driver;
    private SignUpPagePageObject signUpPagePageObject;
    private SignUpPageVerifier signUpPageVerifier;
    private SignUpModel signUpModel;

    /**
     * Sets up test data and creates page object instances.
     */
    @Before
    public void setUp() throws TestExecutionException {
        LOG.info("Deleting existing records");
        if (given().delete(ServiceTestingProperties.REST_API_URL).getStatusCode() == HttpStatus.SC_OK) {
            LOG.info("Records were deleted successfully");
        } else {
            LOG.info("Something went wrong! Existing records couldn't be deleted");
        }

        LOG.info("Reading test data from CSV file");
        final List<CSVRestTestInput> testData = CSVReaderUtilitySingleton.getInstance().getIntput(DEFAULT_TEST_INPUT_FILE, DEFAULT_TEST_PARAMETERS);
        if (!testData.isEmpty()) {
            CSVRestTestInput testInput = testData.get(0);
            signUpModel = SignUpModel.builder()
                    .firstName(testInput.getFirstName())
                    .lastName(testInput.getLastName())
                    .email(testInput.getEmailAddress())
                    .emailConfirmation(testInput.getEmailAddressConfirmation())
                    .wantNewslettes(testInput.isNewsletterOptIn())
                    .build();
        }

        LOG.info("Initializing Firefox driver");
        driver = new FirefoxDriver();
        LOG.info("Opening subscription page");
        driver.get("https://t7-f0x.rhcloud.com/subscription/subscription.html");

        signUpPagePageObject = new SignUpPagePageObject(driver);
        signUpPageVerifier = new SignUpPageVerifier(signUpPagePageObject);
    }

    /**
     * Signs up with the given data. Makes basic verifications about the page display and elements behavior.
     */
    @Test
    public void signUpSubscriber() throws TestExecutionException {
        signUpPageVerifier.checkSignUpPageFields();
        signUpPageVerifier.checkSignUpPageHeaders();
        signUpPagePageObject.signUp(signUpModel);
        ThankYouPageVerifier thankYouPageVerifier = new ThankYouPageVerifier(new ThankYouPagePageObject(driver));
        thankYouPageVerifier.whenSubscribeFinishedCheckDataOnPage(signUpModel.getFirstName(), signUpModel.getEmail());
    }

    /**
     * Closes the browser after test execution.
     */
    @After
    public void tearDown() {
        LOG.info("Closing browser");
        if (driver != null) {
            driver.quit();
        }
    }
}


