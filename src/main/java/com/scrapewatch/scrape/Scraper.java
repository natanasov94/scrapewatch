package com.scrapewatch.scrape;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import com.scrapewatch.dto.ScrapedEmailsDTO;
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

    public ScrapedEmailsDTO scrapeEmails(String url) throws IOException {
        log.info("Scraping emails from: " + url);
        Document doc = jsoupConnection.establishConnectionAndLoadJavascript(url);
        Elements mailToElements = doc.select("a[href^=mailto]");

        List<String> emails = new ArrayList<>();
        for (Element mailToElement : mailToElements) {
            String href = mailToElement.attr("href");
            String email = href.substring("mailto:".length());
            log.info("BaseUrl: " + url + " - Found email: " + email);
            emails.add(email);
        }
        return ScrapedEmailsDTO.builder().baseUrl(url).emails(emails).build();
    }
}
