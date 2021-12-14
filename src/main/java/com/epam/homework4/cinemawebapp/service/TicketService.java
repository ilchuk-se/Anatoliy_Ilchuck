package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getById(final Long id);

    List<Ticket> getAll();

    Ticket create(final Ticket ticket);

    void delete(final Long id);
}
