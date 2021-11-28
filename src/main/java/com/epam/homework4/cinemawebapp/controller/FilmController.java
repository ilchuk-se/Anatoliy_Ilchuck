package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.exception.FilmControllerException;
import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.service.IFilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FilmController {

    private final IFilmService filmService;
    private static final String message = "Can not cast film id to int";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/film")
    public List<Film> getAllFilms() {
        return  filmService.listFilms();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/film/{id}")
    public Film getFilmById(@PathVariable String id) throws FilmControllerException {
        Film film = null;
        try {
            Long filmId = Long.parseLong(id);
            film = filmService.getFilmById(filmId);
        }catch (NumberFormatException ex){
            log.info(message);
            throw new FilmControllerException(message, ex.getCause());
        }
        return film;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/film")
    public Film createFilm(@RequestBody Film film){
        return  filmService.createFilm(film);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/film/{id}")
    public Film updateFilm(@PathVariable String id, @RequestBody @Valid Film film) throws FilmControllerException {
        Film filmUpdated = null;
        try {
            Long filmId = Long.parseLong(id);
            filmUpdated = filmService.updateFilm(filmId, film);
        }catch (NumberFormatException ex){
            log.info(message);
            throw new FilmControllerException(message, ex.getCause());
        }
        return filmUpdated;
    }

    @DeleteMapping(value = "/film/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable String id) throws FilmControllerException {
        try {
            Long filmId = Long.parseLong(id);
            filmService.deleteFilm(filmId);
        }catch (NumberFormatException ex){
            log.info(message);
            throw new FilmControllerException(message, ex.getCause());
        }

        return ResponseEntity.noContent().build();
    }
}
