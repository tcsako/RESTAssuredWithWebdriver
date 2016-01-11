package com.epam.newsletter;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.service.client.SignUpServiceClient;
import com.epam.restassured.service.client.SubscriberServiceClient;

public class SignUpServiceHardCodedTestDataTest {

    private static final Logger LOG = LogManager.getLogger(SignUpServiceDDTTest.class);
    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL_ADDRESS = "johndoe@freecloud.com";
    private static final String EMAIL_ADDRESS_CONFIRMATION = "johndoe@freecloud.com";
    private static final String NEWSLETTER_OPT_IN = "true";

    private SubscriberServiceClient subscriberServiceClient;

    /**
     * Responsible for setting up test data and environment.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    	LOG.info("Starting before to delete all existing subsribers");

    	subscriberServiceClient = new SubscriberServiceClient();
        subscriberServiceClient.deleteSubscribers();

        LOG.info("Before is finished");
    }

    /**
     * Performs a REST subscription then checks the data correctness.
     *
     * @throws Exception
     */
    @Test
    public void should_Subsribe_When_ValidUser() {
    	LOG.info("Sign up with user and verify subscriber");
        new SignUpServiceClient().signUp(SignUpModel.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL_ADDRESS)
                .emailConfirmation(EMAIL_ADDRESS_CONFIRMATION)
                .wantNewsletters(Boolean.parseBoolean(NEWSLETTER_OPT_IN)).build());
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(EMAIL_ADDRESS));
    }
}
