package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.businesslogic.OptionalCheckerImpl;
import com.epam.homework4.cinemawebapp.model.Hall;
import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.repository.TicketRepository;
import com.epam.homework4.cinemawebapp.service.TicketService;
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
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final OptionalChecker<Ticket> ticketOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Ticket getById(Long id) {
        log.info("getTicket by id {}", id);
        return ticketOptionalChecker.getValueIfPresent(
                        ticketRepository.findById(id),
                "Ticket with id: " + id + " was not found."
        );
    }

    @Override
    public List<Ticket> getAll() {
        log.info("get all Tickets");

        Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("id"));
        Page<Ticket> firstTenTickets = ticketRepository.findAll(firstPageWithTenElements);

        return firstTenTickets.toList();
    }

    @Override
    public Ticket create(Ticket ticket) {
        log.info("createTicket with id {}", ticket.getId());
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Long id, Ticket ticket) {
        log.info("updateTicket with id {}", ticket.getId());

        Ticket ticketToUpdate = getById(id);
        ticket.setId(ticketToUpdate.getId());

        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        log.info("deleteTicket with id {}", id);
        ticketRepository.deleteById(id);
    }
}
