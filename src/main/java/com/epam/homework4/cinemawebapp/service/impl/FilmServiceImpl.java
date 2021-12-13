package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.businesslogic.OptionalCheckerImpl;
import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.repository.FilmRepository;
import com.epam.homework4.cinemawebapp.service.FilmService;
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
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final OptionalChecker<Film> filmOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Film getById(Long id) {
        log.info("getFilm by id {}", id);

        return filmOptionalChecker.getValueIfPresent(
                filmRepository.findById(id),
                "Film with id: " + id + " was not found."
        );
    }

    @Override
    public List<Film> getAll() {
        log.info("get all films");

        return Lists.newArrayList(filmRepository.findAll());
    }

    @Override
    public Film create(Film film) {
        log.info("createFilm with name {}", film.getOriginalName());
        return filmRepository.save(film);
    }

    @Override
    public Film update(Long id, Film film) {
        log.info("updateFilm with name {}", film.getOriginalName());

        Film filmToUpdate = filmOptionalChecker.getValueIfPresent(
                filmRepository.findById(id),
                "Film with id: " + id + " not found and can not be updated."
        );
        film.setId(filmToUpdate.getId());

        return filmRepository.save(film);
    }

    @Override
    public void delete(Long id) {
        log.info("deleteFilm with id {}", id);
        filmRepository.deleteById(id);
    }
}
