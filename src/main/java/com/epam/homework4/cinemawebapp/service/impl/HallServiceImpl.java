package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.repository.IHallRepository;
import com.epam.homework4.cinemawebapp.service.IHallService;
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
    public CinemaHall getHallById(int id) {
        log.info("getHall by id {}", id);
        return hallRepository.getHallById(id);
    }

    @Override
    public List<CinemaHall> listHalls() {
        log.info("get all Halls");
        return hallRepository.listHalls();
    }

    @Override
    public CinemaHall createHall(CinemaHall hall) {
        log.info("createUser with name {}", hall.getName());
        return hallRepository.createHall(hall);
    }

    @Override
    public CinemaHall updateHall(int id, CinemaHall hall) {
        log.info("updateUser with name {}", hall.getName());
        return hallRepository.updateHall(id, hall);
    }

    @Override
    public void deleteHall(int id) {
        log.info("deleteHall with id {}", id);
        hallRepository.deleteHall(id);
    }
}
