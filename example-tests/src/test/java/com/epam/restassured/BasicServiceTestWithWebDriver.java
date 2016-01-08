package com.epam.restassured;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.csvreader.model.CSVRestTestInputModel;
import com.epam.restassured.env.EnvironmentProvider;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.pageobjects.NewsletterSignUpPageObject;
import com.epam.restassured.pageobjects.SignUpConfirmationPageObject;
import com.epam.restassured.pageobjects.verifier.NewsletterSignUpPageVerifier;
import com.epam.restassured.pageobjects.verifier.SignUpConfirmationPageVerifier;
import com.epam.restassured.service.client.SubscriberServiceClient;
import com.epam.restassured.url.SignUpPathProvider;
import com.epam.restassured.url.UrlBuilder;
import com.google.common.collect.ImmutableList;

/**
 * Represents an automated subscription to the newsletter with the {@link WebDriver}.
 * Test data is read from a .CSV file.
 * <p>
 * Created by Peter_Olah1 on 12/16/2015.
 */
public class BasicServiceTestWithWebDriver {
    private static final Logger LOG = Logger.getLogger(BasicServiceTestWithWebDriver.class);

    private static final String BASE_URL_PROPERTY = "BASE_URL";
    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_webdriver.csv";
    private static final List<String> DEFAULT_TEST_PARAMETERS = ImmutableList.of("firstName", "lastName", "emailAddress", "emailAddressConfirmation", "newsletterOptIn");

    private WebDriver driver;
    private NewsletterSignUpPageObject newsletterSignUpPageObject;
    private NewsletterSignUpPageVerifier newsletterSignUpPageVerifier;
    private SignUpModel signUpModel;

    /**
     * Sets up test data and creates page object instances.
     */
    @Before
    public void setUp() throws TestExecutionException {
    	LOG.info("Starting Before method in " + this.toString());
        new SubscriberServiceClient().deleteSubscribers();

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

        driver = new FirefoxDriver();
        UrlBuilder urlBuilder = new UrlBuilder(new EnvironmentProvider().get().get(BASE_URL_PROPERTY));
        driver.get(urlBuilder.buildUriFor(new SignUpPathProvider()));

        newsletterSignUpPageObject = new NewsletterSignUpPageObject(driver);
        newsletterSignUpPageVerifier = new NewsletterSignUpPageVerifier(newsletterSignUpPageObject);
    	LOG.info("Before has been finished in " + this.toString());
    }

    /**
     * Signs up with the given data. Makes basic verifications about the page display and elements behavior.
     */
    @Test
    public void shouldSubscribe() throws TestExecutionException {
    	LOG.info("Starting test script " + this.toString());
    	newsletterSignUpPageVerifier.givenSignUpPageFieldsDisplayed();
    	newsletterSignUpPageVerifier.givenSignUpPageHeadersDisplayed();
        newsletterSignUpPageObject.whenSignUp(signUpModel);
        SignUpConfirmationPageVerifier thankYouPageVerifier = new SignUpConfirmationPageVerifier(new SignUpConfirmationPageObject(driver));
        thankYouPageVerifier.whenSubscribeFinishedCheckDataOnPage(signUpModel.getFirstName(), signUpModel.getEmail());
    	LOG.info("Test script has been finished in " + this.toString());
    }

    /**
     * Closes the browser after test execution.
     */
    @After
    public void tearDown() {
        LOG.info("Starting After in" + this.toString() + "to close browser");
        if (driver != null) {
            driver.quit();
        }
    	LOG.info("After has been finished in " + this.toString());
    }
}


