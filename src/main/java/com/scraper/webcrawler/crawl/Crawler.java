package com.scraper.webcrawler.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.scraper.webcrawler.dto.CrawledPagesDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Crawler {
    
    private final List<String> INVALID_URLS = List.of("", "/", "/#");

    public CrawledPagesDTO crawl(String baseUrl) throws IOException {
        List<String> childPages = new ArrayList<>();
        log.info("Fetching child pages from " + baseUrl);
        Document document = Jsoup.connect(baseUrl).get();
        Elements links = document.select("a[href]"); // Select all <a> elements with href attribute

        for (Element link : links) {
            String childUrl = link.absUrl("href"); // Get absolute URL of the link
            if (isDesiredChildPage(childUrl, baseUrl)) {
                log.info("BaseUrl: " + baseUrl + " - Found child: " + childUrl);
                childPages.add(childUrl);
            }
        }
        return new CrawledPagesDTO(baseUrl, childPages);
    }

    private boolean isDesiredChildPage(String childUrl, String baseUrl) {
        boolean startsWithBaseUrl = childUrl.startsWith(baseUrl);
        if (startsWithBaseUrl) {
            boolean childIsBaseUrl = childUrl.equals(baseUrl);
            String childUrlSubstring = childUrl.substring(baseUrl.length(), childUrl.length());
            boolean childIsValidUrl = !INVALID_URLS.contains(childUrlSubstring);
            return !childIsBaseUrl && childIsValidUrl;
        } else {
            return false;
        }
    }

}
