package com.weather.api.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weathers {

    private Weather main;

//    @JsonIgnore
//    @ToString.Exclude
//    private Long dt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private Date dt_txt;

//    @JsonIgnore
//    @ToString.Exclude
//    private Object weather;
//    @JsonIgnore
//    @ToString.Exclude
//    private Object clouds;
//    @JsonIgnore
//    @ToString.Exclude
//    private Object wind;
//    @JsonIgnore
//    @ToString.Exclude
//    private Long visibility;
//    @JsonIgnore
//    @ToString.Exclude
//    private Integer pop;
//    @JsonIgnore
//    @ToString.Exclude
//    private Object snow;
//    @JsonIgnore
//    @ToString.Exclude
//    private Object sys;



}
