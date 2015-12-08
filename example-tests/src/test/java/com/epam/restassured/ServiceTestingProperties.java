package com.epam.restassured;

//TODO: 1. rename to UEL Builder and implement Builder pattern
//TODO: 2. Facade can be used as well
//TODO: URL Builder interface to handle URLs for webdriver scripts

public class ServiceTestingProperties {
	//Base URL for REST API
	public final static String REST_API_URL = "https://t7-f0x.rhcloud.com/subscription/api/subscribers/";
	//Content type for JSON
	public final static String JSON_CONTENT_TYPE = "application/json";

	//Generate URL to search for data using REST API
	public static String getRestApiUrlWithSearchParameter(String searchParam) {
		return REST_API_URL + "?search=" + searchParam;
	}
	
	//Generate URL to post data
	public static String getUrlToPostData(String firstName, String lastName, String emailAddress, String emailAddressConfirmation, String newsletterOptIn) {
		return "https://t7-f0x.rhcloud.com/subscription/subscription.html" +
				"?firstName=" + firstName + 
				"&lastName=" + lastName +
				"&emailAddress=" + emailAddress +
				"&emailAddressConfirmation=" + emailAddressConfirmation +
				"&newsletterOptIn=" + newsletterOptIn +
				"&_newsletterOptIn=on";
	}
}
