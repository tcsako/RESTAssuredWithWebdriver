package com.epam.restassured.env;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

import com.epam.restassured.exception.TestRunError;
import com.google.common.collect.Maps;

public final class EnvironmentProvider implements Supplier<Map<String, String>> {
    private static final String DEFAULT_ENV_PROPERTES = "env.properties";

    @Override
    public Map<String, String> get() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream(DEFAULT_ENV_PROPERTES));
            return Maps.fromProperties(properties);
        } catch (IOException e) {
            throw new TestRunError("Failed to load environment", e);
        }
    }
}
