package com.menet.urlshortener.configuration;

import com.menet.urlshortener.repository.RedisUrlRepository;
import com.menet.urlshortener.repository.UrlRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    UrlRepository urlRepository() {
        return new RedisUrlRepository();
    }
}
