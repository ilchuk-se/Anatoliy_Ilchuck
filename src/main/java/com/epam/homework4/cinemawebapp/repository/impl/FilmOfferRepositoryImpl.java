package com.epam.homework4.cinemawebapp.repository.impl;

import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.repository.IFilmOfferRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmOfferRepositoryImpl implements IFilmOfferRepository {

    private List<FilmOffer> list = new ArrayList<>();

    final int timePerFilm = 180;

    @Override
    public FilmOffer getFilmOfferById(int id) {
        return list.stream()
                .filter(filmOffer -> filmOffer.getId() == id)
                .findFirst()
                .orElseThrow(() ->new RuntimeException("FilmOffer[id=" + id + "] is not found."));
    }

    @Override
    public List<FilmOffer> listFilmOffers() {
        return new ArrayList<>(list);
    }

    @Override
    public FilmOffer createFilmOffer(FilmOffer offer) {
        boolean isFilmExists = list.stream()
                .filter(f -> f.getHallId() == offer.getHallId()
                && f.getFilmId() == offer.getFilmId()
                //&& f.getDate().equals(offer.getDate())
                //&& Duration.between(f.getTime().toLocalTime(), offer.getTime().toLocalTime()).toMinutes() < timePerFilm
                )
                .findFirst().isPresent();
        if(isFilmExists){
            throw new RuntimeException("Offer with time  '" + offer.getId() + "' already exists. Please, enter another name.");
        }

        list.add(offer);
        return offer;
    }

    @Override
    public FilmOffer updateFilmOffer(int id, FilmOffer offer) {
        boolean isDeleted = list.removeIf(f -> f.getId() == id);
        if (isDeleted) {
            list.add(offer);
        } else {
            throw new RuntimeException("Offer is not found!");
        }
        return offer;
    }

    @Override
    public void deleteFilmOffer(int id) {
        list.removeIf(offer -> offer.getId() == id);
    }
}
