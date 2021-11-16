package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.repository.IFilmOfferRepository;
import com.epam.homework4.cinemawebapp.service.IFilmOfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmOfferServiceImpl implements IFilmOfferService {

    private final IFilmOfferRepository filmOfferRepository;

    @Override
    public FilmOffer getFilmOfferById(int id) {
        log.info("getFilmOffer by id {}", id);
        return filmOfferRepository.getFilmOfferById(id);
    }

    @Override
    public List<FilmOffer> listFilmOffers() {
        log.info("get all filmOffers");
        return filmOfferRepository.listFilmOffers();
    }

    @Override
    public FilmOffer createFilmOffer(FilmOffer filmOffer) {
        log.info("createFilmOffer with id {}", filmOffer.getId());
        return filmOfferRepository.createFilmOffer(filmOffer);
    }

    @Override
    public FilmOffer updateFilmOffer(int id, FilmOffer filmOffer) {
        log.info("updateFilmOffer with id {}", filmOffer.getId());
        return filmOfferRepository.updateFilmOffer(id, filmOffer);
    }

    @Override
    public void deleteFilmOffer(int id) {
        log.info("deleteFilmOffer with id {}", id);
        filmOfferRepository.deleteFilmOffer(id);
    }
}
