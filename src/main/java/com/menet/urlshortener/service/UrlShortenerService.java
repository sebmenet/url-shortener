package com.menet.urlshortener.service;

import com.menet.urlshortener.exception.InvalidUrlException;
import com.menet.urlshortener.exception.UrlNotFoundException;
import com.menet.urlshortener.repository.UrlRepository;
import com.menet.urlshortener.util.HashingUtil;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    @Autowired
    UrlRepository urlRepository;

    public String getUrl(String hashedUrl) throws UrlNotFoundException {
        String url = urlRepository.findUrlById(hashedUrl);

        if (url == null) {
            throw new UrlNotFoundException("Original url not found: " + hashedUrl);
        }
        return url;
    }

    public String createUrl(String url) throws InvalidUrlException {
        if (isValid(url)) {
            String hashedUrl = HashingUtil.getMurmurWithUTFCharsetHash(url);
            urlRepository.setUrl(hashedUrl, url);
            return hashedUrl;
        }
        throw new InvalidUrlException("Invalid URL: " + url);
    }

    private boolean isValid(String url) {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        return urlValidator.isValid(url);
    }
}
