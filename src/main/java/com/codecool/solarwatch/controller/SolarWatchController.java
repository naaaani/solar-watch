package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.geocodingAPI.model.CityReport;
import com.codecool.solarwatch.geocodingAPI.service.DirectGeocodingService;
import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunsetRecord;
import com.codecool.solarwatch.sunriseSunsetAPI.service.SunriseSunsetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SolarWatchController {

    private final DirectGeocodingService geocodingService;
    private final SunriseSunsetService sunriseSunsetService;

    public SolarWatchController(DirectGeocodingService geocodingService, SunriseSunsetService sunriseSunsetService) {
        this.geocodingService = geocodingService;
        this.sunriseSunsetService = sunriseSunsetService;
    }

    @GetMapping("/solarwatch")
    public ResponseEntity<?> getSolarTimes(
            @RequestParam(defaultValue = "Budapest") String city,
            @RequestParam(required = false) LocalDate date) {

        if (date == null) {
            date = LocalDate.now();
        }

        CityReport cityCoordinates = geocodingService.getCityCoordinates(city);
        SunriseSunsetRecord sunriseSunset = sunriseSunsetService.getSunriseSunset(cityCoordinates, date);

        return ResponseEntity.ok(sunriseSunset);
    }
}
