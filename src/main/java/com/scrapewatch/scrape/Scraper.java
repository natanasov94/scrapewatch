package com.scrapewatch.scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.scrapewatch.dto.ScrapedImagesDTO;
import com.scrapewatch.dto.ScrapedPageDTO;
import com.scrapewatch.jsoup.JsoupConnection;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class Scraper {

    private final JsoupConnection jsoupConnection;

    public ScrapedPageDTO scrapeTitle(String url) throws IOException {
        log.info("Scraping title from: " + url);
        Document doc = jsoupConnection.establishConnection(url);
        String title = doc.title();
        return ScrapedPageDTO.builder().title(title).build();
    }

    public ScrapedImagesDTO scrapeImages(String url) throws IOException {
        log.info("Scraping images from: " + url);
        Document doc = jsoupConnection.establishConnection(url);
        Elements images = doc.select("img");

        List<String> imageUrls = new ArrayList<>();
        for (Element image : images) {
            String imageUrl = image.absUrl("src");
            log.info("BaseUrl: " + url + " - Found image: " + imageUrl);
            imageUrls.add(imageUrl);
        }
        return ScrapedImagesDTO.builder().baseUrl(url).images(imageUrls).build();
    }
}
