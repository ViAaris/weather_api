package com.weather.api.service;

import com.weather.api.dto.WeatherDTO;
import com.weather.api.model.ResponseFromApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class MapperTests {

    @Autowired
    DataParser dataParser;
    @Autowired
    OpenWeatherApiService openWeatherApiService;
    @Autowired
    Mapper mapper;

    @Test
    public void testMapResponseToWeatherDTO() throws IOException {
        ResponseFromApi responseFromApi = dataParser.parseDataFromJsonToPojo(
                openWeatherApiService.getForecastFromApi(524901L));
        List<WeatherDTO> forecast = mapper.mapResponseToWeatherDTO(responseFromApi);
        forecast.forEach(System.out::println);
    }
}
