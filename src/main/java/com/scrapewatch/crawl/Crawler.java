package com.scrapewatch.crawl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.scrapewatch.dto.CrawledPageDTO;
import com.scrapewatch.jsoup.JsoupConnection;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class Crawler {

    private final JsoupConnection jsoupConnection;

    public CrawledPageDTO crawl(String url) {
        List<String> childPages = new ArrayList<>();
        log.info("Fetching child pages from " + url);
        Document document = jsoupConnection.establishConnection(url);
        Elements links = document.select("a[href]");

        for (Element link : links) {
            String childUrl = link.absUrl("href");
            log.info("BaseUrl: " + url + " - Found child: " + childUrl);
            childPages.add(childUrl);
        }
        return new CrawledPageDTO(url, childPages);
    }

}
