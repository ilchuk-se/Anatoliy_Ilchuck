package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Film;

import java.util.List;

public interface IFilmService {
    Film getFilmById(Long id);

    List<Film> listFilms();

    Film createFilm(Film film);

    Film updateFilm(Long id, Film film);

    void deleteFilm(Long id);
}
