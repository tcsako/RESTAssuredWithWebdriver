
package com.epam.restassured;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.pojo.Content;
import com.epam.restassured.pojo.SubscriberResponse;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleRestTest {
    private static final int HTTP_OK = HttpStatus.SC_OK;
    private static final Logger log = Logger.getLogger(SampleRestDataDrivenTest.class.getName());

    @BeforeClass
    public static void setUp() throws TestExecutionException {
        log.info("*************************");
        log.info("Deleting existing records");
        if (given().delete(ServiceTestingProperties.REST_API_URL).getStatusCode() == HTTP_OK) {
            log.info("Records were deleted successfully");
        } else {
            log.info("Something went wrong! Existing records couldn't be deleted");
        }
    }

    @Test
    public void getAllSubscribers() {
        Response res = get(ServiceTestingProperties.REST_API_URL);
        SubscriberResponse subscriberResponse = res.as(SubscriberResponse.class);
        log.info(res.asString());
        for (Content content : subscriberResponse.getContent()) {
            log.info(content.getFirstName());
            log.info(content.getLastName());
            log.info(content.getEmailAddress());
            log.info(content.getNewsletterOptIn());
            log.info(content.getUuid());
        }
    }

    @Test
    @Ignore
    public void verifyOnlyOneRecord() {
        // given().authentication().basic("username", "password");
        when().get(ServiceTestingProperties.REST_API_URL + "?search=John").then().content("numberOfElements", is(1));
    }

    @Test
    public void addRecord() {
        List<String> listToVerifyEmail = new ArrayList<String>();
        listToVerifyEmail.add("rogermmm@gmail.com");
        given().contentType("application/json").post(
                "https://t7-f0x.rhcloud.com/subscription/subscription.html?firstName=Beluska&lastName=Vagyok&emailAddress=rogermmm@gmail.com&emailAddressConfirmation=rogermmm@gmail.com&newsletterOptIn=true&_newsletterOptIn=on");
        when().get(ServiceTestingProperties.REST_API_URL + "?search=Beluska").then().content("numberOfElements", is(1))
                .and().content("content.emailAddress", equalTo(listToVerifyEmail));
    }

    @Test
    public void verifyResultNumber() {
        Response res = get(ServiceTestingProperties.REST_API_URL + "?search=Beluska");
        log.info(res.asString());
        JsonPath jp = new JsonPath(res.asString());
        assertEquals("Result number should be 1", 1, jp.getInt("numberOfElements"));
    }
}