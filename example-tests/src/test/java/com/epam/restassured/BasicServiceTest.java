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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BasicServiceTest {
	//Test data
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String emailAddressConfirmation;
	private String newsletterOptIn;

	//Verification
	private List<String> listToVerifyEmail;
	private static final int NUMBER_OF_RESPONSE = 1;
	private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
	private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";
	private static final int HTTP_OK = 200;

	@Before
	public void setUp() throws Exception {
		//Delete all existing record
		given().delete(ServiceTestingProperties.REST_API_URL);
		
		//Setup test data
		firstName = "John";
		lastName = "Doe";
		emailAddress = "johndoe@freecloud.com";
		emailAddressConfirmation = "johndoe@freecloud.com";
		newsletterOptIn = "true";
		
		//Setup verification data
		listToVerifyEmail = new ArrayList<String>();
		listToVerifyEmail.add(emailAddress);
	}

	@Test
	public void addRecord() {
		given().contentType(ServiceTestingProperties.JSON_CONTENT_TYPE).
		and().post(ServiceTestingProperties.getUrlToPostData(firstName, lastName, emailAddress, emailAddressConfirmation, newsletterOptIn));
		
		when().get(ServiceTestingProperties.REST_API_URL).
		
		then().statusCode(HTTP_OK).
		and().content(CONTENT_NUMBER_OF_ELEMENTS, is(NUMBER_OF_RESPONSE)).
		and().content(CONTENT_EMAIL_ADDRESS, equalTo(listToVerifyEmail));
	}

}
