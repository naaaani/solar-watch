package com.codecool.solarwatch.geocodingAPI.service;

import com.codecool.solarwatch.geocodingAPI.model.City;
import com.codecool.solarwatch.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class DirectGeocodingService {
    private static final String API_KEY = System.getenv("GEO_API_KEY");
    private final RestTemplate restTemplate;
    private final CityRepository cityRepository;

    public DirectGeocodingService(RestTemplate restTemplate, CityRepository cityRepository) {
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
    }

    public City getCity(String cityName) {
        Optional<City> city = cityRepository.findByName(cityName);
        if (city.isPresent()) {
            return city.get();
        }
        return fetchCity(cityName);
    }

    private City fetchCity(String cityName) {
        String url = String.format(
                "http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s",
                cityName, API_KEY);

        City[] response = restTemplate.getForObject(url, City[].class);
        assert response != null;
        City city = response[0];
        saveCity(city);
        return city;
    }

    private void saveCity(City city) {
        cityRepository.save(city);
    }
}
