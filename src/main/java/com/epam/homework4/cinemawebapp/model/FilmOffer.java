package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class FilmOffer {
    private int id;
    private Date date;
    private Time time;
    private float price;
    private int filmId;
    private int hallId;
}
