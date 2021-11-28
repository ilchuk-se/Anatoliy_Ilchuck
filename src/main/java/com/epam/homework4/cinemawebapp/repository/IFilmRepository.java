package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.Film;
import org.springframework.data.repository.CrudRepository;

public interface IFilmRepository extends CrudRepository<Film, Long> {
}
