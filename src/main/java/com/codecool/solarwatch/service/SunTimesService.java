package com.codecool.solarwatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SunTimesService {
    private static final String API_KEY = "7578b77dd602acb12aa45dad82d521bd";
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SunTimesService.class);

    public SunTimesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
