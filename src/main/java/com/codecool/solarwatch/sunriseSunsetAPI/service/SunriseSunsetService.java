package com.codecool.solarwatch.sunriseSunsetAPI.service;

import com.codecool.solarwatch.geocodingAPI.model.CityReport;
import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunsetRecord;
import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunsetResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Service
public class SunriseSunsetService {

    private final RestTemplate restTemplate;

    public SunriseSunsetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SunriseSunsetRecord getSunriseSunset(CityReport cityReport, LocalDate date) {
        String url = String.format(
                "https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s",
                cityReport.lat(), cityReport.lon(), date);
        var response = restTemplate.getForObject(url, SunriseSunsetResponseModel.class);

        assert response != null;
        return response.results();
    }
}
