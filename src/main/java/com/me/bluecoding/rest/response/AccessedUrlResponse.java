package com.me.bluecoding.rest.response;

public class AccessedUrlResponse {
    private final String fullUrl;
    private final String shortenedUrl;
    private final Integer accessCount;

    public AccessedUrlResponse(String fullUrl, String shortenedUrl, Integer accessCount) {
        this.fullUrl = fullUrl;
        this.shortenedUrl = shortenedUrl;
        this.accessCount = accessCount;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public Integer getAccessCount() {
        return accessCount;
    }
}