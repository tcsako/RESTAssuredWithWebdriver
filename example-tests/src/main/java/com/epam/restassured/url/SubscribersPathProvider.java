package com.epam.restassured.url;

public final class SubscribersPathProvider implements PathProvider {
    @Override
    public String get() {
        return "/api/subscribers/";
    }
}
