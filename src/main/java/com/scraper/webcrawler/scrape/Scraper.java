package com.scraper.webcrawler.scrape;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.scraper.webcrawler.dto.ScrapedPageDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Scraper {

    public ScrapedPageDTO scrape(String url) throws IOException {
        log.info("Scraping information from: " + url);
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        ScrapedPageDTO scrapedPageDTO = ScrapedPageDTO.builder().title(title).build();
        return scrapedPageDTO;
    }
}
