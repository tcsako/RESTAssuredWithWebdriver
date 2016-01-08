package com.epam.restassured;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.model.SubscriberResponse;
import com.epam.restassured.service.client.SignUpServiceClient;
import com.epam.restassured.service.client.SubscriberServiceClient;
import com.jayway.restassured.response.Response;

@RunWith(Parameterized.class)
public class SampleRestDataDrivenTest {
    private static final Logger LOG = Logger.getLogger(SampleRestDataDrivenTest.class.getName());

    @Parameterized.Parameter
    public int expectedNumberOfElements;

    @Parameterized.Parameter(1)
    public int actualNumberOfElements;
    private SubscriberServiceClient subscriberServiceClient;

    /**
     * Sets up the expected and actual parameters for the DDT methods.
     *
     * @return A 2 dimension array.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> testDataSet() {
        return Arrays.asList(new Object[][]{
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1}
        });
    }

    @Before
    public void setUp() throws TestExecutionException {
        new SubscriberServiceClient().deleteSubscribers();
        subscriberServiceClient = new SubscriberServiceClient();
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
        response.then().content("numberOfElements", is(expectedNumberOfElements))
                .and().content("content.emailAddress", contains(model.getEmail()));
        assertThat(response.getBody().path("numberOfElements"), equalTo(1));
    }
}
