package com.weather.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.api.model.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DataParser {


    /**
     * @param response json from openweathermap api consisting 5 day / 3 hour forecast data (40 timestamps)
     *   The method extracts the necessary data (city id, city name, temperature, date and time) from api response
     *                 and maps it to a Weather object
     * @return the list of Weather objects (40)
     */
    public List<Weather> parseDataFromJsonToWeather(ResponseEntity<String> response) throws JsonProcessingException {
        List<Weather> weather = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());
        long cityId = root.get("city").get("id").asLong();
        String cityName = root.get("city").get("name").asText();
        JsonNode list = root.get("list");
        if (list.isArray()) {
            for (final JsonNode objNode : list) {
                double temp = objNode.get("main").get("temp").asDouble();
                String dateString = objNode.get("dt_txt").asText();
                LocalDateTime date = LocalDateTime.parse(dateString, formatter);
                weather.add(new Weather(cityId, cityName, date, temp));
            }
        }
        return weather;
    }
}
