package com.epam.homework4.cinemawebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmOfferDto {
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private Time time;

    @NotNull
    @Min(0)
    private float price;

    @NotNull
    private FilmDto film;

    @NotNull
    @Min(0)
    private int hallId;
}
