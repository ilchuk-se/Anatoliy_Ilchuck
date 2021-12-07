package com.epam.homework4.cinemawebapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Data
@NoArgsConstructor
public class FilmDto {
    private Long id;

    @NotNull
    @Size(min=1)
    private String originalName;

    @NotNull
    @Size(min=1)
    private String localizedName;

    @NotNull
    @Size(min=1)
    private String description;

    private float imdbRating;

    @NotNull
    private Time timekeeping;

    private String posterImageDir;
}
