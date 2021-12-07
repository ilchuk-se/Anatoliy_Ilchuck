package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.repository.IFilmOfferRepository;
import com.epam.homework4.cinemawebapp.service.IFilmOfferService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmOfferServiceImpl implements IFilmOfferService {

    private final IFilmOfferRepository filmOfferRepository;
    private final OptionalChecker<FilmOffer> filmOfferOptionalChecker = new OptionalChecker<>();

    @Override
    public FilmOffer getFilmOfferById(Long id) {
        log.info("getFilmOffer by id {}", id);
        return filmOfferOptionalChecker.getValueIfPresent(
                filmOfferRepository.findById(id),
                "Film offer with id: " + id + " was not found."
        );
    }

    @Override
    public List<FilmOffer> listFilmOffers() {
        log.info("get all filmOffers");
        return Lists.newArrayList(filmOfferRepository.findAll());
    }

    @Override
    public FilmOffer createFilmOffer(FilmOffer filmOffer) {
        log.info("createFilmOffer with id {}", filmOffer.getId());
        return filmOfferRepository.save(filmOffer);
    }

    @Override
    public FilmOffer updateFilmOffer(Long id, FilmOffer filmOffer) {
        log.info("updateFilmOffer with id {}", filmOffer.getId());

        FilmOffer filmOfferToUpdate = filmOfferOptionalChecker.getValueIfPresent(
                filmOfferRepository.findById(id),
                "Film offer with id: " + id + " not found and can not be updated."
        );

        filmOffer.setId(filmOfferToUpdate.getId());

        return filmOfferRepository.save(filmOffer);
    }

    @Override
    public void deleteFilmOffer(Long id) {
        log.info("deleteFilmOffer with id {}", id);
        filmOfferRepository.deleteById(id);
    }
}
