package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.businesslogic.OptionalCheckerImpl;
import com.epam.homework4.cinemawebapp.model.Offer;
import com.epam.homework4.cinemawebapp.repository.OfferRepository;
import com.epam.homework4.cinemawebapp.service.OfferService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public final class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OptionalChecker<Offer> offerOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Offer getById(final Long id) {
        return offerOptionalChecker.getValueIfPresent(
                offerRepository.findById(id),
                "Film offer with id: " + id + " was not found."
        );
    }

    @Override
    public List<Offer> getAll() {
        return Lists.newArrayList(offerRepository.findAll());
    }

    @Override
    public Offer create(final Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public void delete(final Long id) {
        offerRepository.deleteById(id);
    }
}
