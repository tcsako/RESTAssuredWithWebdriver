package com.epam.restassured.service.client;

import com.epam.restassured.env.EnvironmentProvider;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.url.PathProvider;
import com.epam.restassured.url.SignUpPathProvider;
import com.epam.restassured.url.UrlBuilder;
import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import java.util.Map;

public class SignUpServiceClient {
    private final UrlBuilder urlBuilder = new UrlBuilder(new EnvironmentProvider().get().get("BASE_URL"));
    private final PathProvider pathProvider = new SignUpPathProvider();

    public Response signUp(SignUpModel model) {
        return RestAssured.given().contentType(ContentType.JSON).when().post(
                urlBuilder.withQuery(getQueryFor(model)).buildUriFor(pathProvider));
    }

    private Map<String, String> getQueryFor(SignUpModel model) {
        return ImmutableMap.<String, String>builder()
                .put("firstName", model.getFirstName())
                .put("lastName", model.getLastName())
                .put("emailAddress", model.getEmail())
                .put("emailAddressConfirmation", model.getEmailConfirmation())
                .put("newsletterOptIn", model.getWantNewsletters().toString())
                .put("_newsletterOptIn", model.getWantNewsletters() ? "on" : "off").build();
    }
}
