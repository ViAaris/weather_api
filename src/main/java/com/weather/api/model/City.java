package com.weather.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private long id;
    private String name;
//    @JsonIgnore
//    @ToString.Exclude
//    private Object coord;
//    @JsonIgnore
//    @ToString.Exclude
//    private String country;
//    @JsonIgnore
//    @ToString.Exclude
//    private int population;
//    @JsonIgnore
//    @ToString.Exclude
//    private int timezone;
//    @JsonIgnore
//    @ToString.Exclude
//    private int sunrise;
//    @JsonIgnore
//    @ToString.Exclude
//    private int sunset;
}
