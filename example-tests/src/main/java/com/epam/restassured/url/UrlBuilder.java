package com.epam.restassured.url;

import java.util.Map;
import java.util.Objects;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.collect.ImmutableList;

public class UrlBuilder {
    private final UriComponentsBuilder uriComponentsBuilder;

    public UrlBuilder(String baseUrl) {
        uriComponentsBuilder = UriComponentsBuilder.fromUriString(Objects.requireNonNull(baseUrl));
    }

    public UrlBuilder forPath(String path) {
        uriComponentsBuilder.path(path);
        return this;
    }

    public UrlBuilder withQuery(Map<String, String> query) {
        uriComponentsBuilder.queryParams(query.entrySet().stream()
                .collect(LinkedMultiValueMap::new, (map, entry) -> map.put(entry.getKey(), ImmutableList.of(entry.getValue())), LinkedMultiValueMap::putAll));
        return this;
    }

    public String buildUri() {
        return uriComponentsBuilder.build().toUriString();
    }

    public String buildUriFor(PathProvider pathProvider) {
        return forPath(pathProvider.get()).buildUri();
    }
}
