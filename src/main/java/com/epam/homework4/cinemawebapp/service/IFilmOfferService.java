package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.FilmOffer;

import java.util.List;

public interface IFilmOfferService {
    FilmOffer getFilmOfferById(int id);

    List<FilmOffer> listFilmOffers();

    FilmOffer createFilmOffer(FilmOffer offer);

    FilmOffer updateFilmOffer(int id, FilmOffer offer);

    void deleteFilmOffer(int id);
}
