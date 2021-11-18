package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Data
@Builder
public class Film {
    @NotNull
    @Min(0)
    private int id;

    private String originalName;

    @NotNull
    @Min(1)
    private String localizedName;

    @NotNull
    @Min(10)
    private String description;

    private float imdbRating;

    private Time timekeeping;

    private String posterImageDir;
}
