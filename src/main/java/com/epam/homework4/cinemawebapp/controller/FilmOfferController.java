package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.FilmOfferDto;
import com.epam.homework4.cinemawebapp.mapper.FilmOfferMapper;
import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.service.IFilmOfferService;
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
public class FilmOfferController {

    private final IFilmOfferService filmOfferService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/offer")
    public List<FilmOfferDto> getAllFilmOffers() {
        return FilmOfferMapper.INSTANCE.mapFilmOfferDtos(
                filmOfferService.listFilmOffers()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/offer/{id}")
    public FilmOfferDto getFilmOfferById(@PathVariable Long id) {
        return FilmOfferMapper.INSTANCE.mapFilmOfferDto(
                filmOfferService.getFilmOfferById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/offer")
    public FilmOfferDto createFilmOffer(@RequestBody FilmOfferDto filmOfferDto){
        FilmOffer filmOfferDateToCreate = FilmOfferMapper.INSTANCE.mapFilmOffer(filmOfferDto);
        return FilmOfferMapper.INSTANCE.mapFilmOfferDto(
                filmOfferService.createFilmOffer(filmOfferDateToCreate)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/offer/{id}")
    public FilmOfferDto updateFilmOffer(@PathVariable Long id, @RequestBody @Valid FilmOfferDto filmOfferDto) {
        FilmOffer filmOffer = FilmOfferMapper.INSTANCE.mapFilmOffer(filmOfferDto);
        return FilmOfferMapper.INSTANCE.mapFilmOfferDto(
                filmOfferService.updateFilmOffer(id, filmOffer)
        );
    }

    @DeleteMapping(value = "/offer/{id}")
    public ResponseEntity<Void> deleteFilmOffer(@PathVariable Long id) {
        filmOfferService.deleteFilmOffer(id);
        return ResponseEntity.noContent().build();
    }
}
