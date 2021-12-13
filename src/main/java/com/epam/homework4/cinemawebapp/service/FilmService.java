package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Film;

import java.util.List;

public interface FilmService {
    Film getById(Long id);

    List<Film> getAll();

    Film create(Film film);

    Film update(Long id, Film film);

    void delete(Long id);
}
