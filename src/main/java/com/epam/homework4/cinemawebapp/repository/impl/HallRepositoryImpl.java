package com.epam.homework4.cinemawebapp.repository.impl;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.repository.IHallRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HallRepositoryImpl implements IHallRepository {

    private List<CinemaHall> list = new ArrayList<>();

    @Override
    public CinemaHall getHallById(int id) {
        return list.stream()
                .filter(hall -> hall.getId() == id)
                .findFirst()
                .orElseThrow(() ->new RuntimeException("Hall[id=" + id + "] is not found."));
    }

    @Override
    public List<CinemaHall> listHalls() {
        return new ArrayList<>(list);
    }

    @Override
    public CinemaHall createHall(CinemaHall hall) {
        boolean isHallExists = list.stream().filter(h -> h.getName().equals(hall.getName()))
                .findFirst().isPresent();
        if(isHallExists){
            throw new RuntimeException("Hall with name '" + hall.getName() + "' already exists. Please, enter another name.");
        }

        list.add(hall);
        return hall;
    }

    @Override
    public CinemaHall updateHall(int id, CinemaHall hall) {
        boolean isDeleted = list.removeIf(h -> h.getId() == id);
        if (isDeleted) {
            list.add(hall);
        } else {
            throw new RuntimeException("Hall is not found!");
        }
        return hall;
    }

    @Override
    public void deleteHall(int id) {
        list.removeIf(hall -> hall.getId() == id);
    }
}
