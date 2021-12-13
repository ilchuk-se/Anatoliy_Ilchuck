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
public class HallServiceImpl implements HallService{

    private final HallRepository hallRepository;
    private final OptionalChecker<Hall> hallOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Hall getById(Long id) {
        log.info("getHall by id {}", id);
        return hallOptionalChecker.getValueIfPresent(
                hallRepository.findById(id),
                "Hall with id: " + id + " not found."
        ) ;
    }

    @Override
    public List<Hall> getAll() {
        log.info("get all Halls");
        return Lists.newArrayList(
                hallRepository.findAll()
        );
    }

    @Override
    public Hall create(Hall hall) {
        log.info("createUser with name {}", hall.getName());
        return hallRepository.save(hall);
    }

    @Override
    public Hall update(Long id, Hall hall) {
        log.info("updateUser with name {}", hall.getName());

        Hall hallToUpdate = hallOptionalChecker.getValueIfPresent(
                hallRepository.findById(id),
                "Hall with id: " + id + " not found and can not be updated."
        );
        hall.setId(hallToUpdate.getId());

        return hallRepository.save(hall);
    }

    @Override
    public void delete(Long id) {
        log.info("deleteHall with id {}", id);
        hallRepository.deleteById(id);
    }
}
