package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.CinemaHall;

import java.util.List;

public interface IHallRepository {

    CinemaHall getHallById(int id);

    List<CinemaHall> listHalls();

    CinemaHall createHall(CinemaHall hall);

    CinemaHall updateHall(int id, CinemaHall hall);

    void deleteHall(int id);
}
