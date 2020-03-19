package com.menet.urlshortener.repository;

public interface UrlRepository {
    String findUrlById(String id);

    void setUrl(String hashed, String url);
}
