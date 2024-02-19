package com.weather.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.api.dto.WeatherDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class WeatherService {

    private static final Logger log = Logger.getLogger("WEATHER SERVICE");
    private final OpenWeatherApiService openWeatherApiService;

    public WeatherService(OpenWeatherApiService openWeatherApiService) {
        this.openWeatherApiService = openWeatherApiService;
    }

    /**
     * converts kelvins either to fahrenheit or celsius depending on unit parameter
     */
    public static double convertFromKelvins(double kelvins, String unit) {
        switch (unit) {
            case "fahrenheit":
                return Math.round(kelvins * 9 / 5 - 459.67);
            case "celsius":
                return Math.round(kelvins - 273.15);
        }
        return 0.0;
    }


    /**
     * The method operates Weather objects for the required city:
     * puts to a map values of the temperature (in celsius) for the next 5 days
     *
     * @return the map of 5 objects (key=date, value=temperature)
     */
    @Cacheable("weather")
    public HashMap<String, Double> getTempForFiveDays(long cityId) throws IOException {
        log.info("cacheable weathers service is called (temp  for 5 days)");
        List<WeatherDTO> weathers = openWeatherApiService.getWeatherDTOs(cityId);
        HashMap<String, Double> fiveDaysTemps = new LinkedHashMap<>();
        for (WeatherDTO weather : weathers) {
            LocalDateTime date = weather.getDate();
            if ((date.getDayOfMonth() != LocalDate.now().getDayOfMonth())) {
                fiveDaysTemps.put(weather.formattedDate(), convertFromKelvins(weather.getTemperature(), "celsius"));
            }
        }
        return fiveDaysTemps;
    }

    /**
     * The method operates Weather objects for the required city: extracts a Weather object of the next day, 12am
     * @return the weather object
     */
    public WeatherDTO getNextDay(long cityId) throws IOException {
        WeatherDTO nextDay = null;
        List<WeatherDTO> weathers = openWeatherApiService.getWeatherDTOs(cityId);
        for (WeatherDTO weather : weathers) {
            LocalDateTime date = weather.getDate();
            if ((date.getDayOfMonth() == LocalDate.now().getDayOfMonth() + 1)
                    &&
                    (date.getHour() == 12)) {
                nextDay = weather;
                break;
            }
        }
        return nextDay;
    }

    /**
     * @param unit - required temperature unit - celsius/fahrenheit
     * @param temperature - certain temperature to compare the next day temperature with
     * @param citiesId - a list of the cities to compare
     * The method detects in which of the requested cities the temperature will be above a certain temperature the next day:
     * gets the temperature of the next day for each of the cities and compares it with the temperature param.
     * @return the list of the strings - names of the cities where the next day temperature is higher than the temperature param.
     */
    public List<String> getSummaryCities(String unit, int temperature, List<Long> citiesId) throws IOException {
        List<String> cities = new ArrayList<>();
        for(Long id : citiesId){
            WeatherDTO nextDay = getNextDay(id);
            double nextDayTemp = convertFromKelvins(nextDay.getTemperature(), unit);
            if(nextDayTemp > temperature){
                cities.add(nextDay.getCityName());
            }
        }
        return cities;
    }
}
