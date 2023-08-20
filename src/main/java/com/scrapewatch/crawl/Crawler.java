package com.scrapewatch.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.scrapewatch.dto.CrawledPageDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Crawler {

    private final List<String> INVALID_URLS = List.of("", "#.*", "/", "/#");

    public CrawledPageDTO crawl(String baseUrl) throws IOException {
        List<String> childPages = new ArrayList<>();
        log.info("Fetching child pages from " + baseUrl);
        Document document = Jsoup.connect(baseUrl).get();
        Elements links = document.select("a[href]");

        for (Element link : links) {
            String childUrl = link.absUrl("href");
            if (isDesiredChildPage(childUrl, baseUrl)) {
                log.info("BaseUrl: " + baseUrl + " - Found child: " + childUrl);
                childPages.add(childUrl);
            }
        }
        return new CrawledPageDTO(baseUrl, childPages);
    }

    private boolean isDesiredChildPage(String childUrl, String baseUrl) {
        boolean startsWithBaseUrl = childUrl.startsWith(baseUrl);
        if (!startsWithBaseUrl) {
            return false;
        }
        boolean childIsBaseUrl = childUrl.equals(baseUrl);
        if (childIsBaseUrl) {
            return false;
        }
        String childUrlSubstring = childUrl.substring(baseUrl.length(), childUrl.length());
        return INVALID_URLS.stream().noneMatch(childUrlSubstring::matches);
    }

}
