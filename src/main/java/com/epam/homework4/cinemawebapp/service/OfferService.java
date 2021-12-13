package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Offer;

import java.util.List;

public interface OfferService {
    Offer getById(Long id);

    List<Offer> getAll();

    Offer create(Offer offer);

    Offer update(Long id, Offer offer);

    void delete(Long id);
}
