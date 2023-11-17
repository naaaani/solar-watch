package com.codecool.solarwatch.sunriseSunsetAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SunriseSunsetRecord(String sunrise, String sunset) {}
