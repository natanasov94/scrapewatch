package com.scrapewatch.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.scrapewatch.crawl.Crawler;
import com.scrapewatch.dto.CrawledPageDTO;
import com.scrapewatch.dto.ScrapedPageDTO;
import com.scrapewatch.scrape.Scraper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScrapeWatchRestController {

    private final Crawler crawler;
    private final Scraper scraper;

    @GetMapping("/crawl")
    @ResponseBody
    public CrawledPageDTO crawlForChildPages(@RequestParam String url) throws IOException {
        return crawler.crawl(url);
    }

    @GetMapping("/scrape")
    @ResponseBody 
    public ScrapedPageDTO testCall(@RequestParam String url) throws IOException {
        return scraper.scrape(url);
    }
}
