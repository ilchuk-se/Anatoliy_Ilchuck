package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import org.springframework.data.repository.CrudRepository;

public interface IHallRepository extends CrudRepository<CinemaHall, Long> {
}
