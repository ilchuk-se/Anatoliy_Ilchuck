package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CinemaHall {
    @NotNull
    @Min(0)
    private int id;

    @NotNull
    @Min(1)
    private int size;

    private String schemeImageDir;

    @NotNull
    @Min(1)
    private String name;
}
