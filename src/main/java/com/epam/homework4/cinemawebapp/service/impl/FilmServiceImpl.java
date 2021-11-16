package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.repository.IFilmRepository;
import com.epam.homework4.cinemawebapp.service.IFilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements IFilmService {

    private final IFilmRepository filmRepository;

    @Override
    public Film getFilmById(int id) {
        log.info("getFilm by id {}", id);
        return filmRepository.getFilmById(id);
    }

    @Override
    public List<Film> listFilms() {
        log.info("get all films");
        return filmRepository.listFilms();
    }

    @Override
    public Film createFilm(Film film) {
        log.info("createFilm with name {}", film.getOriginalName());
        return filmRepository.createFilm(film);
    }

    @Override
    public Film updateFilm(int id, Film film) {
        log.info("updateFilm with name {}", film.getOriginalName());
        return filmRepository.updateFilm(id, film);
    }

    @Override
    public void deleteFilm(int id) {
        log.info("deleteFilm with id {}", id);
        filmRepository.deleteFilm(id);
    }
}
