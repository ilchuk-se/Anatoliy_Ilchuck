package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
}
