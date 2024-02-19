package com.weather.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {

    private double temperature;
    private long cityId;
    private String cityName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime date;

        public String formattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
}
