package com.menet.urlshortener;

import com.menet.urlshortener.controller.UrlShortenerController;
import com.menet.urlshortener.exception.InvalidUrlException;
import com.menet.urlshortener.exception.UrlNotFoundException;
import com.menet.urlshortener.repository.UrlRepository;
import com.menet.urlshortener.util.HashingUtil;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UrlShortenerControllerTest {

    @Autowired
    UrlShortenerController urlShortenerController;

    @MockBean
    UrlRepository urlRepository;

    @Before
    public void setUp() {
        Mockito.when(urlRepository.findUrlById(AdditionalMatchers
                .not(HashingUtil.getMurmurWithUTFCharsetHash("http://google.com")))).thenReturn(null);
}

    @Test()
    public void createUrlTest_InvalidUrl() {
        String url = "test.url";
        Exception ex = assertThrows(InvalidUrlException.class, () -> urlShortenerController.create(url));
        assertEquals("Invalid URL: " + url, ex.getMessage());
    }

    @Test
    public void createUrlTest_ValidUrl() throws Exception {
        String url = "http://google.com";
        String expected = HashingUtil.getMurmurWithUTFCharsetHash(url);
        Mockito.doNothing().when(urlRepository).setUrl(expected, url);
        assertEquals(expected, urlShortenerController.create(url));
    }

    @Test
    public void getUrlTest_NotFoundUrl() {
        String originalUrl = "http://facebook.com";
        String id = HashingUtil.getMurmurWithUTFCharsetHash(originalUrl);
        Exception ex = assertThrows(UrlNotFoundException.class, () -> urlShortenerController.getUrl(id));
        assertEquals("Original url not found: " + id, ex.getMessage());
    }

    @Test
    public void getUrlTest_FoundUrl() throws Exception {
        String originalUrl = "http://google.com";
        String id = HashingUtil.getMurmurWithUTFCharsetHash(originalUrl);
        Mockito.when(urlRepository.findUrlById(id)).thenReturn(originalUrl);
        assertEquals(originalUrl, urlShortenerController.getUrl(id));
    }
}
