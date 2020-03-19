package com.menet.urlshortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisUrlRepository implements UrlRepository {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String findUrlById(String id) {
        return stringRedisTemplate.opsForValue().get(id);
    }

    @Override
    public void setUrl(String hashed, String url) {
        stringRedisTemplate.opsForValue().set(hashed, url);
    }
}
