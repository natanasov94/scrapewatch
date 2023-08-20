package com.scraper.webcrawler.rest;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scraper.webcrawler.crawl.Crawler;
import com.scraper.webcrawler.dto.CrawledPagesDTO;
import com.scraper.webcrawler.dto.ScrapedPageDTO;
import com.scraper.webcrawler.scrape.Scraper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScrapeWatchRestController {

    private final Crawler crawler;
    private final Scraper scraper;

    @GetMapping("/crawl")
    @ResponseBody
    public CrawledPagesDTO crawlForChildPages(@RequestParam String url) throws IOException {
        return crawler.crawl(url);
    }

    @GetMapping("/scrape")
    @ResponseBody 
    public ScrapedPageDTO testCall(@RequestParam String url) throws IOException {
        return scraper.scrape(url);
    }
}
