package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.repository.ITicketRepository;
import com.epam.homework4.cinemawebapp.service.ITicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private final ITicketRepository ticketRepository;

    @Override
    public Ticket getTicketById(int id) {
        log.info("getTicket by id {}", id);
        return ticketRepository.getTicketById(id);
    }

    @Override
    public List<Ticket> listTickets() {
        log.info("get all Tickets");
        return ticketRepository.listTickets();
    }

    @Override
    public Ticket createTicket(Ticket Ticket) {
        log.info("createTicket with id {}", Ticket.getId());
        return ticketRepository.createTicket(Ticket);
    }

    @Override
    public Ticket updateTicket(int id, Ticket Ticket) {
        log.info("updateTicket with id {}", Ticket.getId());
        return ticketRepository.updateTicket(id, Ticket);
    }

    @Override
    public void deleteTicket(int id) {
        log.info("deleteTicket with id {}", id);
        ticketRepository.deleteTicket(id);
    }
}
