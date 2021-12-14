package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.businesslogic.OptionalCheckerImpl;
import com.epam.homework4.cinemawebapp.model.Hall;
import com.epam.homework4.cinemawebapp.repository.HallRepository;
import com.epam.homework4.cinemawebapp.service.HallService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public final class HallServiceImpl implements HallService{

    private final HallRepository hallRepository;
    private final OptionalChecker<Hall> hallOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Hall getById(final Long id) {
        return hallOptionalChecker.getValueIfPresent(
                hallRepository.findById(id),
                "Hall with id: " + id + " not found."
        ) ;
    }

    @Override
    public List<Hall> getAll() {
        return Lists.newArrayList(
                hallRepository.findAll()
        );
    }

    @Override
    public Hall create(final Hall hall) {
        return hallRepository.save(hall);
    }

    private Hall editHall(final Hall source, final Hall target){
        source.setId(target.getId());
        return target;
    }

    @Override
    public Hall update(final Long id,final Hall hall) {
        return hallRepository.save(
                editHall(hall, getById(id))
        );
    }

    @Override
    public void delete(final Long id) {
        hallRepository.deleteById(id);
    }
}
