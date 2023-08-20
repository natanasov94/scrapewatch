package com.scrapewatch.scrape;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.scrapewatch.dto.ScrapedPageDTO;

@ExtendWith(MockitoExtension.class)
class ScraperTest {

    Scraper scraper;

    final String HTTPS_URL = "https://example.com";

    @BeforeEach
    void setup() {
        this.scraper = new Scraper();
    }

    @Test
    void testValidScrape() throws IOException {
        ScrapedPageDTO scrapedPageDTO = ScrapedPageDTO.builder().title("Example Domain").build();
        assertEquals(scrapedPageDTO, scraper.scrape(HTTPS_URL));
    }

    @Test
    void testInValidScrape() throws IOException {
        ScrapedPageDTO scrapedPageDTO = ScrapedPageDTO.builder().title("Example Domain").build();
        assertEquals(scrapedPageDTO, scraper.scrape(""));
    }

}
