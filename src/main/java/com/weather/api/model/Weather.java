package com.weather.api.model;

import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private long cityId;
    private String cityName;
    private LocalDateTime date;
    private double temperature;


    public String formattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

}
