package com.epam.restassured;

//TODO: 1. create basic webdriver script + proper page object pattern usage
//TODO: 2. create basic script to do the subscription with webdriver and verify with rest
//TODO: 3. create composition for subscription page with PO (using abstract factory)
//TODO: 4. cerate CSV reader with Singleton design pattern
//TODO: 5. create DDT script for REST script
//TODO: 6. create DDT script for webdriver script
//TODO: 7. create rest script without BDD style (using JUnit asssertions)

import com.epam.restassured.pageobjects.HomePageObject;
import com.epam.restassured.pageobjects.ThankYouPageObject;
import com.epam.restassured.util.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BasicServiceTest {
    // Test data
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String emailAddressConfirmation;
    private boolean subscribeNewsletter;

    // Verification
    private List<String> listToVerifyEmail;
    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";
    private static final int HTTP_OK = 200;
    private HomePageObject homePageObject;
    private ThankYouPageObject thankYouPageObject;

    @Before
    public void setUp() throws Exception {
        // Delete all existing record
        given().delete(ServiceTestingProperties.REST_API_URL);

        // Setup test data
        firstName = "John";
        lastName = "Doe";
        emailAddress = "johndoe@freecloud.com";
        emailAddressConfirmation = "johndoe@freecloud.com";
        subscribeNewsletter = true;

        // Setup verification data
        listToVerifyEmail = new ArrayList<String>();
        listToVerifyEmail.add(emailAddress);
        Driver.getDriver().get("https://t7-f0x.rhcloud.com/subscription/subscription.html");
        homePageObject = new HomePageObject(Driver.getDriver());
    }

    @Test
    public void addRecord() throws Exception {
        //given
        thankYouPageObject = homePageObject.givenSubcribeToNewsletter(firstName, lastName, emailAddress, emailAddressConfirmation, subscribeNewsletter);
        //when
        thankYouPageObject.whenSubscribeFinishedCheckDataOnPage(firstName, emailAddress);
        //when
         when().get(ServiceTestingProperties.REST_API_URL).
         //then
         then().statusCode(HTTP_OK).
         //and
         and().content(CONTENT_NUMBER_OF_ELEMENTS, is(NUMBER_OF_RESPONSE)).
         //and
         and().content(CONTENT_EMAIL_ADDRESS, equalTo(listToVerifyEmail));
    }

    @After
    public void tearDown(){
        Driver.tearDown();
    }

}
