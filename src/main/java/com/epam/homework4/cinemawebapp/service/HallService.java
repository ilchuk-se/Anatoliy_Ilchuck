package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Hall;

import java.util.List;

public interface HallService {
    Hall getById(Long id);

    List<Hall> getAll();

    Hall create(Hall hall);

    Hall update(Long id, Hall hall);

    void delete(Long id);
}
