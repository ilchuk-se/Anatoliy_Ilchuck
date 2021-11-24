package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.repository.ITicketRepository;
import com.epam.homework4.cinemawebapp.service.ITicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private final ITicketRepository ticketRepository;

    @Override
    public Ticket getTicketById(Long id) {
        log.info("getTicket by id {}", id);
        return ticketRepository.findById(id).get();
    }

    @Override
    public List<Ticket> listTickets() {
        log.info("get all Tickets");

        Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("id"));
        Page<Ticket> firstTenTickets = ticketRepository.findAll(firstPageWithTenElements);

        return firstTenTickets.toList();
    }

    @Override
    public Ticket createTicket(Ticket Ticket) {
        log.info("createTicket with id {}", Ticket.getId());
        return ticketRepository.save(Ticket);
    }

    @Override
    public Ticket updateTicket(Long id, Ticket Ticket) {
        log.info("updateTicket with id {}", Ticket.getId());

        Ticket ticketToUpdate = ticketRepository.findById(id).get();
        Ticket.setId(ticketToUpdate.getId());

        return ticketRepository.save(Ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        log.info("deleteTicket with id {}", id);
        ticketRepository.deleteById(id);
    }
}
