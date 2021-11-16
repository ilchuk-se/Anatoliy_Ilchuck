package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CinemaHall {
    private int id;
    private int size;
    private String schemeImageDir;
    private String name;
}
