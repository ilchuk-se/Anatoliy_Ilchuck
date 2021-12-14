package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Film;

import java.util.List;

public interface FilmService {
    Film getById(final Long id);

    List<Film> getAll();

    Film create(final Film film);

    Film update(final Long id,final Film film);

    void delete(final Long id);
}
