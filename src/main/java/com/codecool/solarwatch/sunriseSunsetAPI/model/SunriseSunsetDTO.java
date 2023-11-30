package com.codecool.solarwatch.sunriseSunsetAPI.model;

import java.time.LocalDate;

public class SunriseSunsetDTO {
    private String sunrise;
    private String sunSet;
    private String city;
    private LocalDate date;
    public SunriseSunsetDTO() {
        super();
    }

    public SunriseSunsetDTO(String sunrise, String sunSet, String city, LocalDate date) {
        this.sunrise = sunrise;
        this.sunSet = sunSet;
        this.city = city;
        this.date = date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public String getCity() {
        return city;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
