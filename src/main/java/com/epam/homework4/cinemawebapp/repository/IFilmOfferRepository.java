package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.FilmOffer;
import org.springframework.data.repository.CrudRepository;

public interface IFilmOfferRepository extends CrudRepository<FilmOffer, Long> {
}
