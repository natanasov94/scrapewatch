package com.scraper.webcrawler.rest;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ScrapeWatchRestController {

    @GetMapping("/scrape")
    public String testCall(@RequestParam String url) throws IOException {
        log.info("Attempting to scrape title from: " + url);
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        return "Title: " + title;
    }
    
}
