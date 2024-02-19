package com.weather.api.service;


import com.weather.api.dto.WeatherDTO;
import com.weather.api.model.ResponseFromApi;
import com.weather.api.model.Weathers;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class Mapper {

    private DataParser dataParser;


    @Autowired
    public Mapper(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    public List<WeatherDTO> mapResponseToWeatherDTO(ResponseFromApi response){
        List<WeatherDTO> forecast = new ArrayList<>();
        for(Weathers weathers : response.getList()) {
            WeatherDTO weatherDTO = new WeatherDTO();
            weatherDTO.setCityId(response.getCity().getId());
            weatherDTO.setCityName(response.getCity().getName());

            //LocalDateTime date = LocalDateTime.parse(weathers.formattedDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm"));
            weatherDTO.setDate(weathers.getDt_txt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            weatherDTO.setTemperature(weathers.getMain().getTemp());

            forecast.add(weatherDTO);
        }
        return forecast;
    }


}
