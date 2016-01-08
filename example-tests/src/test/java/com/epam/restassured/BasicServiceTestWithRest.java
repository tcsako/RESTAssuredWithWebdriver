package com.epam.restassured;

//TODO: 1. create basic webdriver script + proper page object pattern usage
//TODO: 2. create basic script to do the subscription with webdriver and verify with rest
//TODO: 3. create composition for subscription page with PO (using abstract factory)
//TODO: 4. cerate CSV reader with Singleton design pattern
//TODO: 5. create DDT script for REST script
//TODO: 6. create DDT script for webdriver script
//TODO: 7. create rest script without BDD style (using JUnit asssertions)

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class BasicServiceTestWithRest {
    private static final Logger LOG = Logger.getLogger(BasicServiceTestWithRest.class);

    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL_ADDRESS = "johndoe@freecloud.com";
    private static final String EMAIL_ADDRESS_CONFIRMATION = "johndoe@freecloud.com";
    private static final String NEWSLETTER_OPT_IN = "true";

    /**
     * Responsible for setting up test data and environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        LOG.info("Deleting existing records");
        if (given().delete(ServiceTestingProperties.REST_API_URL).getStatusCode() == HttpStatus.SC_OK) {
            LOG.info("Records were deleted successfully");
        } else {
            LOG.info("Something went wrong! Existing records couldn't be deleted");
        }
    }

    /**
     * Performs a REST subscription then checks the data correctness.
     *
     * @throws Exception
     */
    @Test
    public void addRecord() {
        given().contentType(ServiceTestingProperties.JSON_CONTENT_TYPE).
                and().post(ServiceTestingProperties.getUrlToPostData(FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, EMAIL_ADDRESS_CONFIRMATION, NEWSLETTER_OPT_IN));
        when().get(ServiceTestingProperties.REST_API_URL).
                then().statusCode(HttpStatus.SC_OK).
                and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE))).
                and().content(CONTENT_EMAIL_ADDRESS, contains(EMAIL_ADDRESS));
    }
}
