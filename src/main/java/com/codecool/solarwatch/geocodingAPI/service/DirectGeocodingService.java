package com.codecool.solarwatch.geocodingAPI.service;

import com.codecool.solarwatch.geocodingAPI.model.CityReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DirectGeocodingService {
    private static final String API_KEY = "7578b77dd602acb12aa45dad82d521bd";
    private final RestTemplate restTemplate;

    public DirectGeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CityReport getCityCoordinates(String city) {
        String url = String.format(
                "http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s",
                city, API_KEY);

        CityReport[] response = restTemplate.getForObject(url, CityReport[].class);

        assert response != null;
        CityReport target = response[0];
        return new CityReport(target.name(), target.lat(), target.lon());
    }
}
