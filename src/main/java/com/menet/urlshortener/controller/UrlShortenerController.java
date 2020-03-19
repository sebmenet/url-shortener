package com.menet.urlshortener.controller;

import com.menet.urlshortener.exception.InvalidUrlException;
import com.menet.urlshortener.exception.UrlNotFoundException;
import com.menet.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/url")
@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id) throws UrlNotFoundException {
        return urlShortenerService.getUrl(id);
    }

    @PostMapping
    public String create(@RequestBody String url) throws InvalidUrlException {
        return urlShortenerService.createUrl(url);
    }
}
