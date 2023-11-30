package com.codecool.solarwatch.repository;

import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunset;
import com.codecool.solarwatch.geocodingAPI.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SunriseSunsetRepository extends JpaRepository<SunriseSunset, Long> {
    Optional<SunriseSunset> findByCityAndDate(City city, LocalDate date);
}
