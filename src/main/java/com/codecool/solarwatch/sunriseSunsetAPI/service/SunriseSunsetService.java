package com.codecool.solarwatch.sunriseSunsetAPI.service;

import com.codecool.solarwatch.geocodingAPI.model.City;
import com.codecool.solarwatch.repository.SunriseSunsetRepository;
import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunset;
import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunsetDTO;
import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunsetRecord;
import com.codecool.solarwatch.sunriseSunsetAPI.model.SunriseSunsetResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class SunriseSunsetService {

    private final RestTemplate restTemplate;
    private final SunriseSunsetRepository sunriseSunsetRepository;
    private final ModelMapper modelMapper;

    public SunriseSunsetService(RestTemplate restTemplate, SunriseSunsetRepository sunriseSunsetRepository, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
        this.modelMapper = modelMapper;
    }

    public SunriseSunsetDTO getSunriseSunset(City city, LocalDate date) {
        Optional<SunriseSunset> sunriseSunset = sunriseSunsetRepository.findByCityAndDate(city, date);

        if (sunriseSunset.isPresent()) {
            SunriseSunset data = sunriseSunset.get();
            return new SunriseSunsetDTO(data.getSunrise(), data.getSunset(), data.getCity(), data.getDate());
        }
        return fetchSunriseSunset(city, date);
    }

    public SunriseSunsetDTO fetchSunriseSunset(City city, LocalDate date) {
        String url = String.format(
                "https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s",
                city.getLat(), city.getLon(), date);

        SunriseSunsetResponseModel sunriseSunsetResponseModel = restTemplate.getForObject(url, SunriseSunsetResponseModel.class);
        SunriseSunsetRecord sunriseSunsetRecord = sunriseSunsetResponseModel.results();
        SunriseSunset sunriseSunset = new SunriseSunset(city, date, sunriseSunsetRecord.sunrise(), sunriseSunsetRecord.sunset());
        sunriseSunsetRepository.save(sunriseSunset);

        return modelMapper.map(sunriseSunset, SunriseSunsetDTO.class);
    }

    public void saveSunriseSunset(SunriseSunset sunriseSunset) {
        sunriseSunsetRepository.save(sunriseSunset);
    }
}
