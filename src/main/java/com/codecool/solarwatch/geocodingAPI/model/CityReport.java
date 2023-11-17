package com.codecool.solarwatch.geocodingAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CityReport(String name, double lat, double lon) {}
