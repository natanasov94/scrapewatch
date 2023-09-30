package com.scrapewatch.jsoup;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class JsoupConnection {

    @Value("${webdriver.location}")
    private String webDriverLocation;

    public Document establishConnection(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IllegalArgumentException | ValidationException | IOException ex) {
            log.error(String.format("Provided URL \"%s\" is not a valid URL", url, ex));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Provided URL \"%s\" is not a valid URL", url));
        } catch (Exception ex) {
            log.error("An unhandled exception was encountered", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unhandled exception was encountered");
        }
    }

    @SneakyThrows
    public Document establishConnectionAndLoadJavascript(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new RemoteWebDriver(
                new URL(webDriverLocation),
                chromeOptions);
        webDriver.manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(10));
        webDriver.get(url);
        Document document = Jsoup.parse(webDriver.getPageSource());
        // Potentially overkill, but i've found it's been hanging otherwise
        webDriver.close();
        webDriver.quit();
        return document;
    }
}
