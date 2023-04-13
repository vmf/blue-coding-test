package com.me.bluecoding.domain.service;

import com.me.bluecoding.domain.AccessedUrl;
import com.me.bluecoding.exception.UrlShortenerException;
import com.me.bluecoding.infrastructure.entity.AccessedUrlEntity;
import com.me.bluecoding.infrastructure.repostory.AccessedUrlRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UrlService {
    private static final String URL_PREFIX = "https://anything.com/";
    private final AccessedUrlRepository accessedUrlRepository;

    public UrlService(AccessedUrlRepository accessedUrlRepository) {
        this.accessedUrlRepository = accessedUrlRepository;
    }

    public String shortUrl(String url) {
        try {
            var md = MessageDigest.getInstance("MD5");
            md.update(url.getBytes());
            var digest = md.digest();
            var buffer = new StringBuffer();
            for (var b : digest) {
                buffer.append(String.format("%02x", b & 0xff));
            }

            var bufferToString = buffer.toString();
            saveNewUrl(bufferToString);

            return buildUrl(bufferToString);

        } catch (NoSuchAlgorithmException e) {
            throw new UrlShortenerException(e);
        }
    }

    private String buildUrl(String hash) {
        return URL_PREFIX + hash;
    }

    public String getFullUrlInternal(String url) {
        return null;
    }

    public String getFullUrl(String url) {
        String hash = url.substring(URL_PREFIX.length());
        saveExistingUrl(hash);

        return null;
    }

    public List<AccessedUrl> getFrequentUrls() {
        var entities = accessedUrlRepository.findTop100OrderByAccessCountDesc();
        var accessUrlList = new ArrayList<AccessedUrl>();

        for (var entity : entities) {
            var fullUrl = getFullUrlInternal(entity.getHash());
            accessUrlList.add(new AccessedUrl(fullUrl, buildUrl(entity.getHash()), entity.getAccessCount()));
        }
        return accessUrlList;
    }

    private void saveExistingUrl(String hash) {
        var entity = accessedUrlRepository.findByHash(hash);
        entity.setAccessCount(entity.getAccessCount() + 1);
        accessedUrlRepository.save(entity);
    }

    private void saveNewUrl(String hash) {
        AccessedUrlEntity entity = new AccessedUrlEntity();
        entity.setHash(hash);
        accessedUrlRepository.save(entity);
    }
}
