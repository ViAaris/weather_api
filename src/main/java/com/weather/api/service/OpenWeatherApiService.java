package com.weather.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.api.dto.WeatherDTO;
import com.weather.api.model.ResponseFromApi;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
public class OpenWeatherApiService {
    private static final Logger log = Logger.getLogger("OPENWEATHER API SERVICE");
    private static final String URL = "http://api.openweathermap.org/data/2.5/forecast";
    private final String openWeatherMapKey = System.getenv("WEATHER_API");

    private DataParser dataParser;
    private OkHttpClient client;
    private Mapper mapper;

    @Autowired
    public OpenWeatherApiService(DataParser dataParser, OkHttpClient client, Mapper mapper) {
        this.dataParser = dataParser;
        this.client = client;
        this.mapper = mapper;
    }


    /**
     * @param cityId an id of the city weather forecast of which is required
     * @return response from openweathermap api consisting 5 day / 3 hour forecast data (40 timestamps)
     */
    @Cacheable("weather")
    public Response getForecastFromApi(Long cityId) throws IOException {
        log.info("call getForecastsFromApi");
        String url = String.format(URL + "?id=%d&appid=%s", cityId, openWeatherMapKey);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return client.newCall(request).execute();
    }

    /**
     * @param cityId an id of the city weather forecast of which is required
     *               The method applies parsing to the api response
     * @return ResponseFromApi
     */

    public ResponseFromApi getParsedForecast(Long cityId) throws IOException {
        return dataParser.parseDataFromJsonToPojo(getForecastFromApi(cityId));
    }

    public List<WeatherDTO> getWeatherDTOs(Long cityId) throws IOException {
        return mapper.mapResponseToWeatherDTO(getParsedForecast(cityId));
    }



}
