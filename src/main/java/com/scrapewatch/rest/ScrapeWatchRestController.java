package com.scrapewatch.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scrapewatch.crawl.Crawler;
import com.scrapewatch.dto.CrawledPageDTO;
import com.scrapewatch.dto.ScrapedImagesDTO;
import com.scrapewatch.dto.ScrapedPageDTO;
import com.scrapewatch.scrape.Scraper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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
    public ScrapedPageDTO scrapeForTitle(@RequestParam String url) throws IOException {
        return scraper.scrapeTitle(url);
    }

    @GetMapping("/scrape/image")
    @ResponseBody
    public ScrapedImagesDTO scrapeForImages(@RequestParam String url) throws IOException {
        return scraper.scrapeImages(url);
    }

    @GetMapping("/scrape/imagedisplay")
    @ResponseBody 
    public String displayScrapedImages(@RequestParam String url) throws IOException {
        ScrapedImagesDTO scrapedImagesDTO = scraper.scrapeImages(url);
        StringBuilder html = new StringBuilder("<html><body>");
        scrapedImagesDTO.getImages()
            .forEach(image -> html.append("<img src=\"")
            .append(image)
            .append("\">")
        );
        html.append("</body></html>");
        return html.toString();
    }
}
