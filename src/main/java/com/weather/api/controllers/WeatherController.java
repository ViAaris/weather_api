package com.weather.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.api.service.WeatherService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
public class WeatherController {

    private final Bucket bucket;

    private final WeatherService weatherService;


    /**
     * rate limit - allows the api 7 calls per minute.
     */
    public WeatherController(WeatherService weatherService) {
        Bandwidth limit = Bandwidth.classic(7, Refill.greedy(7, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();

        this.weatherService = weatherService;
    }


    /**
     * @return a list of temperatures for the next 5 days in one specific city.
     * Checks whether the request is allowed by consuming a token from the bucket.
     */
    @GetMapping("/weather/cities/{id}")
    public ResponseEntity getTemperaturesForFiveDays(@PathVariable("id") long cityId) throws JsonProcessingException {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(weatherService.getTempForFiveDays(cityId));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    /**
     * @return a list of the user’s favourite cities where the temperature will be above a certain temperature the next day.
     * Checks whether the request is allowed by consuming a token from the bucket.
     */
    @GetMapping("/weather/summary")
    public ResponseEntity summaryCities(@RequestParam("unit") String unit, @RequestParam("temperature") int temperature,
                                        @RequestParam("cities") List<Long> citiesId) throws JsonProcessingException {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(weatherService.getSummaryCities(unit, temperature, citiesId));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
