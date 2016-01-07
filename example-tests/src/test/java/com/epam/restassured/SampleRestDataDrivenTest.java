package com.epam.restassured;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.pojo.Content;
import com.epam.restassured.pojo.SubscriberResponse;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SampleRestDataDrivenTest {
    private static final int HTTP_OK = HttpStatus.SC_OK;

    private static Logger log = Logger.getLogger(SampleRestDataDrivenTest.class.getName());

    @Parameterized.Parameter
    public int expectedNumberOfElements;

    @Parameterized.Parameter(value = 1)
    public int actualNumberOfElements;

    /**
     *  Sets up the expected and actual parameters for the DDT methods.
     *
     * @return A 2 dimension array.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> testDataSet() {
        return Arrays.asList(new Object[][] {
                {1, 1 } ,
                {1, 1 } ,
                {1, 1 } ,
                {1, 1 } ,
                {1, 1 }
        });
    }

    /**
     *  Sends a delete request to the given path. Deletes the existing entries
     *  of subsrcibers.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws TestExecutionException {
        log.info("*************************");
        log.info("Deleting existing records");
        if (given().delete(ServiceTestingProperties.REST_API_URL).getStatusCode() == HTTP_OK) {
            log.info("Records were deleted successfully");
        } else {
            log.info("Something went wrong! Existing records couldn't be deleted");
        }
    }

    /**
     *  Gets subsribers data and maps it to the {@link SubscriberResponse} and gets the response as string.
     *
     */
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

    /**
     * Basic verification among the number of existing and the expected records.
     */
    @Test
    @Ignore
    public void verifyOnlyOneRecord() {
        // given().authentication().basic("username", "password");
        when().get(ServiceTestingProperties.REST_API_URL + "?search=John").
                then().content("numberOfElements", is(expectedNumberOfElements));
        //TODO felgöngyölíteni az ügyet
    }

    /**
     *  Creates a record with the data given in the URL, then gets subscriber data and
     *  makes basic verifications for assure the correctness of the sent and stored data.
     *
     */
    @Test
    public void addRecord() {
        List<String> listToVerifyEmail = new ArrayList<String>();
        listToVerifyEmail.add("rogermmm@gmail.com");
        given().contentType("application/json")
                .post("https://t7-f0x.rhcloud.com/subscription/subscription.html?"
                        + "firstName=Beluska&lastName=Vagyok&emailAddress=rogermmm@gmail.com&emailAddressConfirmation=rogermmm@gmail.com&newsletterOptIn=true&_newsletterOptIn=on");
        when().get(ServiceTestingProperties.REST_API_URL + "?search=Beluska").
                then().content("numberOfElements", is(expectedNumberOfElements)).and()
                .content("content.emailAddress", equalTo(listToVerifyEmail));
    }

    /**
     * Verifies the equality the number of the stored an the expected results.
     *
     */
    @Test
    public void verifyResultNumber() {
        assertEquals("Result number should be 1", expectedNumberOfElements, actualNumberOfElements);
    }
}
