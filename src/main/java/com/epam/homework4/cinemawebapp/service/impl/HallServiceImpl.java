package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.repository.IHallRepository;
import com.epam.homework4.cinemawebapp.service.IHallService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HallServiceImpl implements IHallService{

    private final IHallRepository hallRepository;

    @Override
    public CinemaHall getHallById(Long id) {
        log.info("getHall by id {}", id);
        return hallRepository.findById(id).get();
    }

    @Override
    public List<CinemaHall> listHalls() {
        log.info("get all Halls");

        List<CinemaHall> allHalls = Lists.newArrayList(hallRepository.findAll());

        return allHalls;
    }

    @Override
    public CinemaHall createHall(CinemaHall hall) {
        log.info("createUser with name {}", hall.getName());
        return hallRepository.save(hall);
    }

    @Override
    public CinemaHall updateHall(Long id, CinemaHall hall) {
        log.info("updateUser with name {}", hall.getName());

        CinemaHall hallToUpdate = hallRepository.findById(id).get();
        hall.setId(hallToUpdate.getId());

        return hallRepository.save(hall);
    }

    @Override
    public void deleteHall(Long id) {
        log.info("deleteHall with id {}", id);
        hallRepository.deleteById(id);
    }
}
