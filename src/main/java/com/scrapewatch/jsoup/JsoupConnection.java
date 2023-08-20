package com.scrapewatch.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JsoupConnection {

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

}
