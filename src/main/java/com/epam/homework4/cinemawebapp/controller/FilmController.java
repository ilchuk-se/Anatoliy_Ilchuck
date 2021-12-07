package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.FilmDto;
import com.epam.homework4.cinemawebapp.mapper.FilmMapper;
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
    private static final String CAST_EXCEPTION_MESSAGE = "Can not cast film id to int";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/film")
    public List<FilmDto> getAllFilms() {
        return FilmMapper.INSTANCE.mapFilmDtos(filmService.listFilms());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/film/{id}")
    public FilmDto getFilmById(@PathVariable Long id) {
        return FilmMapper.INSTANCE.mapFilmDto(filmService.getFilmById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/film")
    public FilmDto createFilm(@RequestBody FilmDto filmDto){
        Film film = FilmMapper.INSTANCE.mapFilm(filmDto);
        return FilmMapper.INSTANCE.mapFilmDto(filmService.createFilm(film));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/film/{id}")
    public FilmDto updateFilm(@PathVariable Long id, @RequestBody @Valid FilmDto filmDto) {
        Film filmDataToUpdate = FilmMapper.INSTANCE.mapFilm(filmDto);
        return FilmMapper.INSTANCE.mapFilmDto(filmService.updateFilm(id, filmDataToUpdate));
    }

    @DeleteMapping(value = "/film/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}
