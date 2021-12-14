package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Hall;

import java.util.List;

public interface HallService {
    Hall getById(final Long id);

    List<Hall> getAll();

    Hall create(final Hall hall);

    Hall update(final Long id,final Hall hall);

    void delete(final Long id);
}
