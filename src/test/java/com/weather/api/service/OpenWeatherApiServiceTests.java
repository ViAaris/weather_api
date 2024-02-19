package com.weather.api.service;


import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class OpenWeatherApiServiceTests {
    @Autowired
    OpenWeatherApiService openWeatherApiService;

    @Test
    public void testGetForecastFromApi() throws IOException {
        Response response = openWeatherApiService.getForecastFromApi(524901L);
        Assertions.assertNotNull(response);
        ResponseBody body = response.body();
        System.out.println(body.string());
    }
}
