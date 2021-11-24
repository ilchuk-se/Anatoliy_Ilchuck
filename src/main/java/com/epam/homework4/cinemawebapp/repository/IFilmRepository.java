package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.model.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFilmRepository extends CrudRepository<Film, Long> {
}
