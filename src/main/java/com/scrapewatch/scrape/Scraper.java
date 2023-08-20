package com.scrapewatch.scrape;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.scrapewatch.dto.ScrapedPageDTO;
import com.scrapewatch.jsoup.JsoupConnection;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class Scraper {

    private final JsoupConnection jsoupConnection;

    public ScrapedPageDTO scrape(String url) throws IOException {
        log.info("Scraping information from: " + url);
        Document doc = jsoupConnection.establishConnection(url);
        String title = doc.title();
        ScrapedPageDTO scrapedPageDTO = ScrapedPageDTO.builder().title(title).build();
        return scrapedPageDTO;
    }
}
