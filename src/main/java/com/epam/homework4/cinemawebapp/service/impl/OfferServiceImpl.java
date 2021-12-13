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
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OptionalChecker<Offer> offerOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Offer getById(Long id) {
        log.info("getOffer by id {}", id);
        return offerOptionalChecker.getValueIfPresent(
                offerRepository.findById(id),
                "Film offer with id: " + id + " was not found."
        );
    }

    @Override
    public List<Offer> getAll() {
        log.info("get all Offers");
        return Lists.newArrayList(offerRepository.findAll());
    }

    @Override
    public Offer create(Offer offer) {
        log.info("createOffer with id {}", offer.getId());
        return offerRepository.save(offer);
    }

    @Override
    public Offer update(Long id, Offer offer) {
        log.info("updateOffer with id {}", offer.getId());

        Offer offerToUpdate = offerOptionalChecker.getValueIfPresent(
                offerRepository.findById(id),
                "Film offer with id: " + id + " not found and can not be updated."
        );

        offer.setId(offerToUpdate.getId());

        return offerRepository.save(offer);
    }

    @Override
    public void delete(Long id) {
        log.info("deleteOffer with id {}", id);
        offerRepository.deleteById(id);
    }
}
