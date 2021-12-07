package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.repository.IFilmRepository;
import com.epam.homework4.cinemawebapp.service.IFilmService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements IFilmService {

    private final IFilmRepository filmRepository;
    private final OptionalChecker<Film> filmOptionalChecker = new OptionalChecker<>();

    @Override
    public Film getFilmById(Long id) {
        log.info("getFilm by id {}", id);

        return filmOptionalChecker.getValueIfPresent(
                filmRepository.findById(id),
                "Film with id: " + id + " was not found."
        );
    }

    @Override
    public List<Film> listFilms() {
        log.info("get all films");

        return Lists.newArrayList(filmRepository.findAll());
    }

    @Override
    public Film createFilm(Film film) {
        log.info("createFilm with name {}", film.getOriginalName());
        return filmRepository.save(film);
    }

    @Override
    public Film updateFilm(Long id, Film film) {
        log.info("updateFilm with name {}", film.getOriginalName());

        Film filmToUpdate = filmOptionalChecker.getValueIfPresent(
                filmRepository.findById(id),
                "Film with id: " + id + " not found and can not be updated."
        );
        film.setId(filmToUpdate.getId());

        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Long id) {
        log.info("deleteFilm with id {}", id);
        filmRepository.deleteById(id);
    }
}
