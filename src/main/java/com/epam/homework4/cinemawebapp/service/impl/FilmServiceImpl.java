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

@Slf4j
@Service
@RequiredArgsConstructor
public final class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final OptionalChecker<Film> filmOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Film getById(final Long id) {
        return filmOptionalChecker.getValueIfPresent(
                filmRepository.findById(id),
                "Film with id: " + id + " was not found."
        );
    }

    @Override
    public List<Film> getAll() {
        return Lists.newArrayList(filmRepository.findAll());
    }

    @Override
    public Film create(final Film film) {
        return filmRepository.save(film);
    }

    private Film editFilm(final Film source, final Film target){
        source.setId(target.getId());
        return source;
    }

    @Override
    public Film update(final Long id,final Film film) {
        return filmRepository.save(
                editFilm(film, getById(id))
        );
    }

    @Override
    public void delete(final Long id) {
        filmRepository.deleteById(id);
    }
}
