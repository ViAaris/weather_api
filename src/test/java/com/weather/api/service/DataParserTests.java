package com.weather.api.service;

import com.weather.api.model.ResponseFromApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class DataParserTests {

    @Autowired
    DataParser dataParser;
    @Autowired
    OpenWeatherApiService openWeatherApiService;

    @Test
    void testParseDataFromJsonToWeather() throws IOException {
        ResponseFromApi responseFromApi = dataParser.parseDataFromJsonToPojo(
                openWeatherApiService.getForecastFromApi(524901L));
        System.out.println(responseFromApi);
    }
}
