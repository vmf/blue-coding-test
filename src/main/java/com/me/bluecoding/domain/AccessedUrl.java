package com.me.bluecoding.domain;

public class AccessedUrl {
    private final String fullUrl;
    private final String shortenedUrl;
    private final Integer accessCount;

    public AccessedUrl(String fullUrl, String shortenedUrl, Integer accessCount) {
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
