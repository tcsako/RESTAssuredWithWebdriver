package com.epam.restassured;

import com.epam.restassured.pojo.Content;
import com.epam.restassured.pojo.SubscriberResponse;
import com.jayway.restassured.response.Response;
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
    @Parameterized.Parameter
    public int expectedNumberOfElements;

    @Parameterized.Parameter(value = 1)
    public int actualNumberOfElements;

    @Parameterized.Parameters
    public static Collection<Object[]> testDataSet() {
        return Arrays.asList(new Object[][] {
                { 1, 1 } ,
                { 1, 2 } ,
                { 1, 3 } ,
                { 1, 4 } ,
                { 1, 5 }
        });
    }

    @Before
    public void setUp() throws Exception {
        given().delete("https://t7-f0x.rhcloud.com/subscription/api/subscribers/");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void getAllSubsribers() {
        Response res = get("https://t7-f0x.rhcloud.com/subscription/api/subscribers/");
        SubscriberResponse subscriberResponse = res.as(SubscriberResponse.class);
        System.out.println("***************");
        System.out.println(res.asString());
        System.out.println("***************");
        for (Content content : subscriberResponse.getContent()) {
            System.out.println(content.getFirstName());
            System.out.println(content.getLastName());
            System.out.println(content.getEmailAddress());
            System.out.println(content.getNewsletterOptIn());
            System.out.println(content.getUuid());
            System.out.println("***************");
        }
    }

    @Test
    @Ignore
    public void deletaAllSubscribers() {
        Response res = delete("https://t7-f0x.rhcloud.com/subscription/api/subscribers/");
        System.out.println(res.statusCode());
    }

    @Test
    @Ignore
    public void verifyOnlyOneRecord() {
        // given().authentication().basic("username", "password");
        when().get("https://t7-f0x.rhcloud.com/subscription/api/subscribers/?search=John").
                then().content("numberOfElements", is(expectedNumberOfElements));
    }

    @Test
    public void addRecord() {
        List<String> list = new ArrayList<String>();
        list.add("rogermmm@gmail.com");
        given().contentType("application/json")
                .post("https://t7-f0x.rhcloud.com/subscription/subscription.html?firstName=Beluska&lastName=Vagyok&emailAddress=rogermmm@gmail.com&emailAddressConfirmation=rogermmm@gmail.com&newsletterOptIn=true&_newsletterOptIn=on");
        when().get("https://t7-f0x.rhcloud.com/subscription/api/subscribers/?search=Beluska").
                then().content("numberOfElements", is(expectedNumberOfElements)).and()
                .content("content.emailAddress", equalTo(list));
    }

    @Test
    @Ignore
    public void verifyResultNumber() {
        assertEquals("Result number should be 1", expectedNumberOfElements, actualNumberOfElements);
    }
}
