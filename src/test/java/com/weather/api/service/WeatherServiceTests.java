package com.weather.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
public class WeatherServiceTests {

    @Autowired
    OpenWeatherApiService openWeatherApiService;
    @Autowired
    WeatherService weatherService;

    @Test
    public void getTempForFiveDays() throws IOException {
        HashMap<String, Double> fiveDaysTemps = weatherService.getTempForFiveDays(524901L);
        fiveDaysTemps.entrySet().forEach(System.out::println);
    }
}
