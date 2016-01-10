package com.epam.restassured;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.service.client.SignUpServiceClient;
import com.epam.restassured.service.client.SubscriberServiceClient;
import com.jayway.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SignUpServiceNoBDDTest {
    private static final Logger LOG = LogManager.getLogger(SignUpServiceDDTTest.class);
    private SubscriberServiceClient subscriberServiceClient;

    @Before
    public void setUp() throws TestExecutionException {
    	LOG.info("Starting before to delete all existing subsribers");

    	subscriberServiceClient = new SubscriberServiceClient();
        subscriberServiceClient.deleteSubscribers();

        LOG.info("Before is finished");
    }

    @Test
    public void should_Subsribe_When_ValidUser() {
    	LOG.info("Set up test data");
    	SignUpModel model = SignUpModel.builder()
                .firstName("Beluska")
                .lastName("Vagyok")
                .email("rogermmm@gmail.com")
                .emailConfirmation("rogermmm@gmail.com")
                .wantNewsletters(true)
                .build();

    	LOG.info("Sign up with: " + model.toString());
    	new SignUpServiceClient().signUp(model);

    	LOG.info("Verify subsriber");
    	Response response = subscriberServiceClient.searchFor(model.getFirstName());
        //response.then().content("numberOfElements", is(equalTo(1)));
        assertThat(response.getBody().path("numberOfElements"), equalTo(1));
    	LOG.info("Verification is finished");
    }
}
