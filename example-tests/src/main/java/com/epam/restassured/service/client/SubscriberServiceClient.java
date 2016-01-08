package com.epam.restassured.service.client;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.epam.restassured.env.EnvironmentProvider;
import com.epam.restassured.url.SubscribersPathProvider;
import com.epam.restassured.url.UrlBuilder;
import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.response.Response;

public class SubscriberServiceClient {
    private static final Logger LOG = Logger.getLogger(SubscriberServiceClient.class);

    private final UrlBuilder urlBuilder = new UrlBuilder(new EnvironmentProvider().get().get("BASE_URL")).forPath(new SubscribersPathProvider().get());

    public Response getSubscribers() {
        return get(getSubscriberServiceUrl());
    }

    public Response deleteSubscribers() {
        LOG.info("Delete existing records");
        Response response = delete(getSubscriberServiceUrl());
        if (response.statusCode() == HttpStatus.SC_OK) {
            LOG.info("Records were deleted successfully");
        } else {
            LOG.info("Something went wrong! Existing records couldn't be deleted");
        }
        return response;
    }

    public Response searchFor(String searchTerm) {
        return get(getSubscriberServiceUrlBuilder().withQuery(ImmutableMap.of("search", searchTerm)).buildUri());
    }

    private String getSubscriberServiceUrl() {
        return getSubscriberServiceUrlBuilder().buildUri();
    }

    private UrlBuilder getSubscriberServiceUrlBuilder() {
        return urlBuilder;
    }
}
