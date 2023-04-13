package com.me.bluecoding.rest.response;

import com.me.bluecoding.domain.AccessedUrl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccessedUrlListResponse {
    private final List<AccessedUrlResponse> urls;

    public AccessedUrlListResponse(List<AccessedUrlResponse> urls) {
        this.urls = urls;
    }

    public static AccessedUrlListResponse createFromDomain(List<AccessedUrl> urls) {
        var responseUrls = urls.stream().map(url -> new AccessedUrlResponse(url.getFullUrl(), url.getShortenedUrl(), url.getAccessCount())).collect(Collectors.toList());
        return new AccessedUrlListResponse(responseUrls);
    }

    public List<AccessedUrlResponse> getUrls() {
        return urls;
    }
}
