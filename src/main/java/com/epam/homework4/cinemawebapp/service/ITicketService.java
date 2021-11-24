package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.model.Ticket;

import java.util.List;

public interface ITicketService {
    Ticket getTicketById(Long id);

    List<Ticket> listTickets();

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Long id, Ticket ticket);

    void deleteTicket(Long id);
}
