package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Offer;

import java.util.List;

public interface OfferService {
    Offer getById(final Long id);

    List<Offer> getAll();

    Offer create(final Offer offer);

    void delete(final Long id);
}
