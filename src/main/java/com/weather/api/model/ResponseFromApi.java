package com.weather.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFromApi {

//    @JsonIgnore
//    @ToString.Exclude
//    private Double cod;
//    @JsonIgnore
//    @ToString.Exclude
//    private String message;
//    @JsonIgnore
//    @ToString.Exclude
//    private Integer cnt;

    private City city;
    private List<Weathers> list;

}
