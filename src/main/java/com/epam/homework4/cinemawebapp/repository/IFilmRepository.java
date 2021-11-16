package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.model.Film;

import java.util.List;

public interface IFilmRepository {
    Film getFilmById(int id);

    List<Film> listFilms();

    Film createFilm(Film film);

    Film updateFilm(int id, Film film);

    void deleteFilm(int id);
}
