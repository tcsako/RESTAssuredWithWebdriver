package com.epam.restassured;

import com.epam.restassured.pageobjects.HomePageObject;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Peter_Olah1 on 12/16/2015.
 */
public class SubscriptionWithWebDriverTest {

    private Logger log = Logger.getLogger(BasicServiceTest.class.getName());
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String emailAddressConfirmation;
    private String baseURL;
    private boolean subscribeNewsletter;
    private HomePageObject homePageObject;
    private ThankYouPageVerifier thankYouPageVerifier;
    private HomePageVerifier homePageVerifier;
    private WebDriver driver;

    /**
     * Sets up test data and create page object instances.
     *
     */
    @Before
    public void setUp() {

        given().delete(ServiceTestingProperties.REST_API_URL);

        log.info("Setting up test data");
        firstName = "Jasper";
        lastName = "Testaverde";
        emailAddress = "jtmsq@jt.qw";
        emailAddressConfirmation = "jtmsq@jt.qw";
        baseURL = "https://t7-f0x.rhcloud.com/subscription/subscription.html";
        subscribeNewsletter = true;

        log.info("Setting up verification data");
        log.info("Initializing Firefox driver");
        driver = new FirefoxDriver();
        log.info("Opening subscription page");
        driver.get(baseURL);
        homePageObject = new HomePageObject(driver);
        thankYouPageVerifier = new ThankYouPageVerifier(driver);
        homePageVerifier = new HomePageVerifier(driver);
    }

    /**
     *  Signs up with the given data. Makes basic verifications about the page display and elements behavior.
     *
     */
    @Test
    public void signUpSubscriber() {
        homePageVerifier.getInstance();
        homePageVerifier.checkHomePageFields();
        homePageVerifier.checkHomePageHeaders();
        homePageObject.givenSignUp(firstName, lastName, emailAddress, emailAddressConfirmation, subscribeNewsletter);
        thankYouPageVerifier.whenSubscribeFinishedCheckDataOnPage(firstName, emailAddress);
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

