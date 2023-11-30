package com.codecool.solarwatch.sunriseSunsetAPI.model;

import com.codecool.solarwatch.geocodingAPI.model.City;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SunriseSunset {
    @Id
    private long id;
    @ManyToOne
    private City city;
    private LocalDate date;
    private String sunrise;
    private String sunset;

    public SunriseSunset() {}

    public SunriseSunset(City city, LocalDate date, String sunrise, String sunset) {
        this.city = city;
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCity() {
        return this.city.getName();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
