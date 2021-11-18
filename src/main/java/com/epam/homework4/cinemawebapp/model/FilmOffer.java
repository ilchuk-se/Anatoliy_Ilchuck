package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class FilmOffer {
    @NotNull
    @Min(0)
    private int id;

    @Future
    private Date date;

    private Time time;

    @NotNull
    @Min(0)
    private float price;

    @NotNull
    @Min(0)
    private int filmId;

    @NotNull
    @Min(0)
    private int hallId;
}
