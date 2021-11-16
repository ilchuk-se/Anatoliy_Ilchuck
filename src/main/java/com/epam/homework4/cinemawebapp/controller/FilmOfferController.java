package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.exception.FilmOfferControllerException;
import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.service.IFilmOfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FilmOfferController {

    private final IFilmOfferService filmOfferService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/offer")
    public List<FilmOffer> getAllFilmOffers() {
        return  filmOfferService.listFilmOffers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/offer/{id}")
    public FilmOffer getFilmOfferById(@PathVariable String id) throws FilmOfferControllerException {
        FilmOffer filmOffer = null;
        try {
            int filmOfferId = Integer.parseInt(id);
            filmOffer = filmOfferService.getFilmOfferById(filmOfferId);
        }catch (NumberFormatException ex){
            String message = "Can not cast filmOffer id to int";
            log.info(message);
            throw new FilmOfferControllerException(message, ex.getCause());
        }
        return filmOffer;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/offer")
    public FilmOffer createFilmOffer(@RequestBody FilmOffer filmOffer){
        return  filmOfferService.createFilmOffer(filmOffer);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/offer/{id}")
    public FilmOffer updateFilmOffer(@PathVariable String id, @RequestBody FilmOffer filmOffer) throws FilmOfferControllerException {
        FilmOffer filmOfferUpdated = null;
        try {
            int filmOfferId = Integer.parseInt(id);
            filmOfferUpdated = filmOfferService.updateFilmOffer(filmOfferId, filmOffer);
        }catch (NumberFormatException ex){
            String message = "Can not cast filmOffer id to int";
            log.info(message);
            throw new FilmOfferControllerException(message, ex.getCause());
        }
        return filmOfferUpdated;
    }

    @DeleteMapping(value = "/offer/{id}")
    public ResponseEntity<Void> deleteFilmOffer(@PathVariable String id) throws FilmOfferControllerException {
        try {
            int filmOfferId = Integer.parseInt(id);
            filmOfferService.deleteFilmOffer(filmOfferId);
        }catch (NumberFormatException ex){
            String message = "Can not cast filmOffer id to int";
            log.info(message);
            throw new FilmOfferControllerException(message, ex.getCause());
        }

        return ResponseEntity.noContent().build();
    }
}
