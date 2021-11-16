package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.model.Ticket;

import java.util.List;

public interface ITicketRepository {
    Ticket getTicketById(int id);

    List<Ticket> listTickets();

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(int id, Ticket ticket);

    void deleteTicket(int id);
}
