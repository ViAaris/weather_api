package com.weather.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.api.model.Weather;
import com.weather.api.service.DataParser;
import com.weather.api.service.OpenWeatherApiService;
import com.weather.api.service.WeatherService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApplicationTests {

    private static final Logger log = Logger.getLogger("TEST");

    @Autowired
    WeatherService weatherService;

    @Autowired
    OpenWeatherApiService openWeatherApiService;

    @Autowired
    DataParser dataParser;

    private final String openWeatherMapKey = System.getenv("WEATHER_API");

    @Test
    void contextLoads() {
    }

    @Test
    void getEnvVar() {
        System.out.println(System.getenv("WEATHER_API"));
    }

//    @Test
//    void getFiveDaysForecast() throws JsonProcessingException {
//        weatherService.getTempForFiveDays(524901).forEach((k, v) -> System.out.println(k + " " + v));
//    }
//
//    @Test
//    void testSummary() throws JsonProcessingException {
//        List<String> cities = weatherService.getSummaryCities("celsius", 15,
//                Arrays.asList(2618425L, 3621849L, 3133880L));
//        cities.forEach(System.out::println);
//    }
//

//
//    @Test
//    void testParsing() throws JsonProcessingException {
//        ResponseEntity<String> response = openWeatherApiService.getForecastFromApi(524901L);
//        List<Weather> weather = dataParser.parseDataFromJsonToWeather(response);
//        Assertions.assertEquals(40, weather.size());
//        weather.forEach(System.out::println);
//    }
//
//    @Test
//    void testBucket() {
//        Bucket bucket = Bucket4j.builder()
//                .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))))
//                .addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofSeconds(20))))
//                .build();
//
//        for (int i = 1; i <= 5; i++) {
//            assertTrue(bucket.tryConsume(1));
//        }
//        assertFalse(bucket.tryConsume(1));
//    }
//
//
//    @Test
//    void testToCelsius() {
//        Assertions.assertEquals(13.0, WeatherService.convertFromKelvins(286.18, "celsius"));
//    }
//
//    @Test
//    void testCache() throws JsonProcessingException, InterruptedException {
//        openWeatherApiService.getForecastFromApi(2618425L);
//        log.info("before second invocation..");
//        openWeatherApiService.getForecastFromApi(2618425L);
//        log.info("before third invocation..");
//        openWeatherApiService.getForecastFromApi(2618425L);
//        log.info("now it's gonna sleep...");
//        Thread.sleep(10000);
//        log.info("before first invocation after sleep..");
//        openWeatherApiService.getForecastFromApi(2618425L);
//        log.info("before second invocation after sleep..");
//        openWeatherApiService.getForecastFromApi(2618425L);
//
//    }

}
