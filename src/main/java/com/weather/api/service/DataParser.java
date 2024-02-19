package com.weather.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weather.api.model.ResponseFromApi;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataParser {


    /**
     * @param response json from openweathermap api consisting 5 day / 3 hour forecast data (40 timestamps)
     *   The method parses the response from the api to a Java object
     * @return ResponseFromApi object
     */
    public ResponseFromApi parseDataFromJsonToPojo(Response response) throws IOException {


        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();

        ResponseFromApi resp = mapper.readValue(response.body().string(), ResponseFromApi.class);
        return resp;


//        JsonNode root = mapper.readTree(response.getBody());
//        long cityId = root.get("city").get("id").asLong();
//        String cityName = root.get("city").get("name").asText();
//        JsonNode list = root.get("list");
//        if (list.isArray()) {
//            for (final JsonNode objNode : list) {
//                double temp = objNode.get("main").get("temp").asDouble();
//                String dateString = objNode.get("dt_txt").asText();
//                LocalDateTime date = LocalDateTime.parse(dateString, formatter);
//                weather.add(new Weather(cityId, cityName, date, temp));
//            }
//        }

    }
}
