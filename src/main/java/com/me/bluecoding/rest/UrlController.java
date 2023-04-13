package com.me.bluecoding.rest;

import com.me.bluecoding.rest.response.AccessedUrlListResponse;
import com.me.bluecoding.rest.response.UrlResponse;
import com.me.bluecoding.domain.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/short-url/**", method = RequestMethod.POST)
    public UrlResponse shortUrl(HttpServletRequest request) {
        var url = extractUrlFromRequest(request);
        return new UrlResponse(urlService.shortUrl(url));
    }

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public UrlResponse getFullUrl(HttpServletRequest request) {
        var url = extractUrlFromRequest(request);
        return new UrlResponse(urlService.getFullUrl(url));
    }

    @GetMapping("/frequent-urls")
    public AccessedUrlListResponse getMostAccessedUrls() {
        var urls = urlService.getFrequentUrls();
        return AccessedUrlListResponse.createFromDomain(urls);
    }

    private static String extractUrlFromRequest(HttpServletRequest request) {
        var host = request.getHeader("host");
        return request.getRequestURL().toString().split(host)[1].substring(1);
    }
}
