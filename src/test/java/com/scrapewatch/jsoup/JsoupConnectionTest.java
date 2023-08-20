package com.scrapewatch.jsoup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.web.server.ResponseStatusException;

public class JsoupConnectionTest {

    JsoupConnection jsoupConnection;

    @BeforeEach
    void setup() {
        this.jsoupConnection = new JsoupConnection();
    }

    @Test
    void testValid() {
        assertEquals(Document.class,
                jsoupConnection.establishConnection("https://example.com").getClass());
    }

    @ParameterizedTest
    @MethodSource("invalidUrls")
    void testInvalid(String url) {
        assertThrows(
                ResponseStatusException.class,
                () -> jsoupConnection.establishConnection(url),
                String.format("Provided URL \"%s\" is not a valid URL", url));
    }

    private static List<String> invalidUrls() {
        return List.of("", "null", "http", "http://", "asd", "http://asd");
    }

}
