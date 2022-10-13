package com.weather.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.api.model.Weather;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OpenWeatherApiService {
    private static final Logger log = Logger.getLogger("OPENWEATHER API SERVICE");
    private static final String URL = "http://api.openweathermap.org/data/2.5/forecast";
    private final String openWeatherMapKey = System.getenv("WEATHER_API");

    private final DataParser dataParser;

    public OpenWeatherApiService(DataParser dataParser) {
        this.dataParser = dataParser;
    }


    /**
     * @param cityId an id of the city weather forecast of which is required
     * @return response from openweathermap api consisting 5 day / 3 hour forecast data (40 timestamps)
     */
    public ResponseEntity<String> getForecastFromApi(Long cityId) {
        log.info("call getForecastsFromApi");
        String url = String.format(URL + "?id=%d&appid=%s", cityId, openWeatherMapKey);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;

    }

    /**
     * @param cityId an id of the city weather forecast of which is required
     *               The method applies parsing to the api response
     * @return the list of Weather objects (40)
     */
    @Cacheable("weather")
    public List<Weather> getParsedForecast(Long cityId) throws JsonProcessingException {
        return dataParser.parseDataFromJsonToWeather(getForecastFromApi(cityId));
    }




}
