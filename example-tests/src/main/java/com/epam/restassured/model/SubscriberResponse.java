package com.epam.restassured.model;

import java.util.List;

import com.google.common.base.MoreObjects;

public class SubscriberResponse {
    private List<Content> content;
    private String last;
    private String totalPages;
    private String totalElements;
    private String first;
    private String sort;
    private String numberOfElements;
    private String size;
    private String number;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(String numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(SubscriberResponse.class)
                .add("content", content)
                .add("last", last)
                .add("totalPages", totalPages)
                .add("totalElements", totalElements)
                .add("first", first)
                .add("sort", sort)
                .add("numberOfElements", numberOfElements)
                .add("size", size)
                .add("number", number).toString();
    }
}
