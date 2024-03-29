package com.weather.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private Double temp;

//    @JsonIgnore
//    @ToString.Exclude
//    private Double feels_like;
//    @JsonIgnore
//    @ToString.Exclude
//    private Float temp_min;
//    @JsonIgnore
//    @ToString.Exclude
//    private Float temp_max;
//    @JsonIgnore
//    @ToString.Exclude
//    private Integer pressure;
//    @JsonIgnore
//    @ToString.Exclude
//    private Float sea_level;
//    @JsonIgnore
//    @ToString.Exclude
//    private Float grnd_level;
//    @JsonIgnore
//    @ToString.Exclude
//    private Integer humidity;
//    @JsonIgnore
//    @ToString.Exclude
//    private Float temp_kf;
}
