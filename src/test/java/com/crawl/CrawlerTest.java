package com.crawl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scrapewatch.crawl.Crawler;
import com.scrapewatch.dto.CrawledPageDTO;
import com.scrapewatch.jsoup.JsoupConnection;

public class CrawlerTest {

    JsoupConnection jsoupConnection;
    Crawler crawler;

    final String HTTPS_URL = "https://example.com";

    @BeforeEach
    void setup() {
        this.jsoupConnection = new JsoupConnection();
        this.crawler = new Crawler(jsoupConnection);
    }

    @Test
    void testValidCrawl() {
        CrawledPageDTO crawledPageDTO = crawler.crawl(HTTPS_URL);
        assertEquals(crawledPageDTO.getBaseUrl(), HTTPS_URL);
        assertEquals(crawledPageDTO.getChildPages(), List.of("https://www.iana.org/domains/example"));
    }
}
