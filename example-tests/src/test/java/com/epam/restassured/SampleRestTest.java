package com.epam.restassured;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.pojo.SubscriberResponse;
import com.epam.restassured.service.client.SignUpServiceClient;
import com.epam.restassured.service.client.SubscriberServiceClient;
import com.jayway.restassured.response.Response;

public class SampleRestTest {
    private static final Logger LOG = Logger.getLogger(SampleRestDataDrivenTest.class);
    private SubscriberServiceClient subscriberServiceClient;

    @Before
    public void setUp() throws TestExecutionException {
        subscriberServiceClient = new SubscriberServiceClient();
        subscriberServiceClient.deleteSubscribers();
    }

    @Test
    public void getAllSubscribers() {
        LOG.info(subscriberServiceClient.getSubscribers().as(SubscriberResponse.class));
    }

    @Test
    public void shouldBeAbleToSingUpThroughREST() {
        SignUpModel model = SignUpModel.builder()
                .firstName("Beluska")
                .lastName("Vagyok")
                .email("rogermmm@gmail.com")
                .emailConfirmation("rogermmm@gmail.com")
                .wantNewsletters(true)
                .build();
        new SignUpServiceClient().signUp(model);
        Response response = subscriberServiceClient.searchFor(model.getFirstName());
        response.then().content("numberOfElements", is(equalTo(1)));
        assertThat(response.getBody().path("numberOfElements"), equalTo(1));
    }
}
