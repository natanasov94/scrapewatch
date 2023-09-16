package com.scrapewatch.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScrapedImagesDTO {

    private String baseUrl;
    private List<String> images;

}
