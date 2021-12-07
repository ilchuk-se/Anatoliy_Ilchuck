package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.CinemaHall;

import java.util.List;

public interface IHallService {
    CinemaHall getHallById(Long id);

    List<CinemaHall> listHalls();

    CinemaHall createHall(CinemaHall hall);

    CinemaHall updateHall(Long id, CinemaHall hall);

    void deleteHall(Long id);
}
