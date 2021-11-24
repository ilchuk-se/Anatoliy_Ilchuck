package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IHallRepository extends CrudRepository<CinemaHall, Long> {
}
