package com.scrapewatch.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ScrapeWatchRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testScrapeValid() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/scrape?url=https://example.com"))
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "title": "Example Domain"
                                }
                                """, false))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testScrapeInvalid() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/scrape?url=qwerty"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testCrawlValid() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/crawl?url=https://example.com"))
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "baseUrl": "https://example.com",
                                    "childPages": [
                                        "https://www.iana.org/domains/example"
                                    ]
                                }
                                """, false))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testCrawlInvalid() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/crawl?url=qwerty"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
