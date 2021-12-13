package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getById(Long id);

    List<Ticket> getAll();

    Ticket create(Ticket ticket);

    Ticket update(Long id, Ticket ticket);

    void delete(Long id);
}
