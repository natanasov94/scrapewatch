package com.scrapewatch.scrape;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.scrapewatch.dto.ScrapedPageDTO;
import com.scrapewatch.jsoup.JsoupConnection;

class ScraperTest {

    JsoupConnection jsoupConnection;
    Scraper scraper;

    final String HTTPS_URL = "https://example.com";

    @BeforeEach
    void setup() {
        this.jsoupConnection = new JsoupConnection();
        this.scraper = new Scraper(jsoupConnection);
    }

    @Test
    void testValidScrape() throws IOException {
        ScrapedPageDTO scrapedPageDTO = ScrapedPageDTO.builder().title("Example Domain").build();
        assertEquals(scrapedPageDTO, scraper.scrape(HTTPS_URL));
    }

}
