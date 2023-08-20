package com.scraper.webcrawler.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrawledPageDTO {
    
    private String baseUrl;
    private List<String> childPages;
    
}