package com.epam.restassured.service.client;

import com.epam.restassured.env.EnvironmentProvider;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.url.PathProvider;
import com.epam.restassured.url.SignUpPathProvider;
import com.epam.restassured.url.UrlBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class SignUpServiceClient {
	private final UrlBuilder urlBuilder = new UrlBuilder(new EnvironmentProvider().get().get("BASE_URL"));
	private final PathProvider pathProvider = new SignUpPathProvider();

	public Response signUp(SignUpModel model) {
		return RestAssured.given().contentType(ContentType.JSON).post(urlBuilder.forPath(pathProvider.get()).buildUri().toString() + "?firstName=" + model.getFirstName() + "&lastName=" + model.getLastName() + "&emailAddress="
				+ model.getEmail() + "&emailAddressConfirmation=" + model.getEmailConfirmation() + "&newsletterOptIn="
				+ model.getWantNewsletters() + "&_newsletterOptIn=" + (model.getWantNewsletters() ? "on" : "off"));
	}
}
