package com.epam.restassured;

import com.epam.restassured.pojo.Content;
import com.epam.restassured.pojo.SubscriberResponse;
import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.After;
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


//CSV reader with Singleton implementation
//AbstractFactory implementation (e.g. PageObject pattern)
//Subscribe with browser, verify with REST
//Where to add Delete all
@RunWith(Parameterized.class)
public class SampleRestTest {
    private static Logger log = Logger.getLogger(SampleRestTest.class.getName());

    @Parameterized.Parameter
    private int expectedNumberOfElements;

    @Parameterized.Parameter(value = 1)
    private int actualNumberOfElements;

    /**
     *  Sets up the expected and actual parameters for the DDT methods.
     *
     * @return A 2 dimension array.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> testDataSet() {
        log.info("Setting up DDT data");
        return Arrays.asList(new Object[][] {
                {1, 1 } ,
                {1, 2 } ,
                {1, 3 } ,
                {1, 4 } ,
                {1, 5 }
        });
    }

    /**
     *  Sends a delete request to the given path. Deletes the existing entries
     *  of subsrcibers.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        log.info("***************");
        log.info("Deleting existing records");
        given().delete("https://t7-f0x.rhcloud.com/subscription/api/subscribers/");
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     *  Gets subsribers data and maps it to the {@link SubscriberResponse} and gets the response as string.
     *
     */
    @Test
    @Ignore
    public void getAllSubsribers() {
        Response res = get("https://t7-f0x.rhcloud.com/subscription/api/subscribers/");
        SubscriberResponse subscriberResponse = res.as(SubscriberResponse.class);
        log.info("***************");
        log.info(res.asString());
        log.info("***************");
        for (Content content : subscriberResponse.getContent()) {
            log.info(content.getFirstName());
            log.info(content.getLastName());
            log.info(content.getEmailAddress());
            log.info(content.getNewsletterOptIn());
            log.info(content.getUuid());
            log.info("***************");
        }
    }

    /**
     * Deletes existing subscribers data.
     */
    @Test
    @Ignore
    public void deletaAllSubscribers() {
        Response res = delete("https://t7-f0x.rhcloud.com/subscription/api/subscribers/");
        log.info(res.statusCode());
    }

    /**
     * Basic verification among the number of existing and the expected records.
     */
    @Test
    @Ignore
    public void verifyOnlyOneRecord() {
        // given().authentication().basic("username", "password");
        when().get("https://t7-f0x.rhcloud.com/subscription/api/subscribers/?search=John").
                then().content("numberOfElements", is(expectedNumberOfElements));
    }

    /**
     *  Creates a record with the data given in the URL, then gets subscriber data and
     *  makes basic verifications for assure the correctness of the sent and stored data.
     *
     */
    @Test
    public void addRecord() {
        List<String> list = new ArrayList<String>();
        list.add("rogermmm@gmail.com");
        given().contentType("application/json")
                .post("https://t7-f0x.rhcloud.com/subscription/subscription.html?"
                        + "firstName=Beluska&lastName=Vagyok&emailAddress=rogermmm@gmail.com&emailAddressConfirmation=rogermmm@gmail.com&newsletterOptIn=true&_newsletterOptIn=on");
        when().get("https://t7-f0x.rhcloud.com/subscription/api/subscribers/?search=Beluska").
                then().content("numberOfElements", is(expectedNumberOfElements)).and()
                .content("content.emailAddress", equalTo(list));
    }

    /**
     * Verifies the equality the number of the stored an the expected results.
     *
     */
    @Test
    @Ignore
    public void verifyResultNumber() {
        assertEquals("Result number should be 1", expectedNumberOfElements, actualNumberOfElements);
    }
}
