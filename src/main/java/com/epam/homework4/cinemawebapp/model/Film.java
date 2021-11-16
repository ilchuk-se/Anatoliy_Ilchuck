package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class Film {
    private int id;
    private String originalName;
    private String localizedName;
    private String description;
    private float imdbRating;
    private Time timekeeping;
    private String posterImageDir;
}
