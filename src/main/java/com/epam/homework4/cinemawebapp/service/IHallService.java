package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.model.CinemaHall;

import java.util.List;

public interface IHallService {
    CinemaHall getHallById(int id);

    List<CinemaHall> listHalls();

    CinemaHall createHall(CinemaHall hall);

    CinemaHall updateHall(int id, CinemaHall hall);

    void deleteHall(int id);
}
