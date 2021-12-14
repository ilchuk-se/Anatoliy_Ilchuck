package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.businesslogic.OptionalCheckerImpl;
import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.repository.TicketRepository;
import com.epam.homework4.cinemawebapp.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public final class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final OptionalChecker<Ticket> ticketOptionalChecker = new OptionalCheckerImpl();

    @Override
    public Ticket getById(final Long id) {
        return ticketOptionalChecker.getValueIfPresent(
                        ticketRepository.findById(id),
                "Ticket with id: " + id + " was not found."
        );
    }

    @Override
    public List<Ticket> getAll() {
        Page<Ticket> firstTenTickets = ticketRepository.findAll(
                PageRequest.of(0, 10, Sort.by("id"))
        );
        return firstTenTickets.toList();
    }

    @Override
    public Ticket create(final Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(final Long id) {
        ticketRepository.deleteById(id);
    }
}
