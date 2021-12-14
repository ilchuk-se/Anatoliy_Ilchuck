package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.FilmDto;
import com.epam.homework4.cinemawebapp.mapper.FilmMapper;
import com.epam.homework4.cinemawebapp.service.FilmService;
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
public final class FilmController {

    private final FilmService filmService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/film")
    public List<FilmDto> getAll() {
        return FilmMapper.INSTANCE.mapFilmDtos(
                filmService.getAll()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/film/{id}")
    public FilmDto getById(@PathVariable final Long id) {
        return FilmMapper.INSTANCE.mapFilmDto(
                filmService.getById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/film")
    public FilmDto create(@RequestBody final FilmDto filmDto){
        return FilmMapper.INSTANCE.mapFilmDto(
                filmService.create(FilmMapper.INSTANCE.mapFilm(filmDto))
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/film/{id}")
    public FilmDto update(@PathVariable final Long id, @RequestBody @Valid final FilmDto filmDto) {
        return FilmMapper.INSTANCE.mapFilmDto(
                filmService.update(id, FilmMapper.INSTANCE.mapFilm(filmDto))
        );
    }

    @DeleteMapping(value = "/film/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        filmService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
