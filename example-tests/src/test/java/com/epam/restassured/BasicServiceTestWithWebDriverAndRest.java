package com.epam.restassured;

//TODO: 1. create basic webdriver script + proper page object pattern usage  - [FINISHED]
//TODO: 2. create basic script to do the subscription with webdriver and verify with rest - [FINISHED]
//TODO: 3. create composition for subscription page with PO (using abstract factory) - [FINISHED]
//TODO: 4. cerate CSV reader with Singleton design pattern - [FINISHED]
//TODO: 5. create DDT script for REST script - [FINISHED]
//TODO: 6. create DDT script for webdriver script
//TODO: 7. create rest script without BDD style (using JUnit asssertions)

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.csvreader.model.CSVRestTestInputModel;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.pageobjects.NewsletterSignUpPageObject;
import com.epam.restassured.pageobjects.SignUpConfirmationPageObject;
import com.epam.restassured.pageobjects.verifier.SignUpConfirmationPageVerifier;
import com.epam.restassured.service.client.SubscriberServiceClient;
import com.google.common.collect.ImmutableList;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.hamcrest.Matchers.*;

/**
 * Represents an automated subscription to the newsletter with the {@link WebDriver} and REST validation.
 * Test data is read from a .CSV file.
 * <p>
 * Created by Tamas_Csako
 */
public class BasicServiceTestWithWebDriverAndRest {
    private static final Logger LOG = LogManager.getLogger(BasicServiceTestWithWebDriverAndRest.class);

    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";

    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_rest_and_webdriver.csv";
    private static final List<String> DEFAULT_TEST_PARAMETERS = ImmutableList.of("firstName", "lastName", "emailAddress", "emailAddressConfirmation", "newsletterOptIn");

    private WebDriver driver;
    private SignUpModel signUpModel;
    private SubscriberServiceClient subscriberServiceClient;

    /**
     * Responsible for setting up test data and test environment.
     *
     * @throws TestExecutionException
     */
    @Before
    public void setUp() throws TestExecutionException {
        subscriberServiceClient = new SubscriberServiceClient();
        subscriberServiceClient.deleteSubscribers();

        final List<CSVRestTestInputModel> testData = CSVReaderUtilitySingleton.getInstance().getIntput(DEFAULT_TEST_INPUT_FILE, DEFAULT_TEST_PARAMETERS);
        if (!testData.isEmpty()) {
            CSVRestTestInputModel testInput = testData.get(0);
            signUpModel = SignUpModel.builder()
                    .firstName(testInput.getFirstName())
                    .lastName(testInput.getLastName())
                    .email(testInput.getEmailAddress())
                    .emailConfirmation(testInput.getEmailAddressConfirmation())
                    .wantNewsletters(testInput.isNewsletterOptIn())
                    .build();
        }
        LOG.info("Initializing Firefox driver");
        driver = new FirefoxDriver();
        LOG.info("Opening subscription page");
        driver.get("https://t7-f0x.rhcloud.com/subscription/subscription.html");
    }

    /**
     * Makes an automated subscription then checks the data correctness.
     *
     * @throws TestExecutionException
     */
    @Test
    public void addRecord() throws TestExecutionException {
        new NewsletterSignUpPageObject(driver).whenSignUp(signUpModel);
        SignUpConfirmationPageVerifier signUpConfirmationPageVerifier = new SignUpConfirmationPageVerifier(new SignUpConfirmationPageObject(driver));
        signUpConfirmationPageVerifier.whenSubscribeFinishedCheckDataOnPage(signUpModel.getFirstName(), signUpModel.getEmail());

        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
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
