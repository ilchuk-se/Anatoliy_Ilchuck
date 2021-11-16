package com.epam.homework4.cinemawebapp.repository.impl;

import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.repository.ITicketRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketRepository implements ITicketRepository {

    private List<Ticket> list = new ArrayList<>();

    @Override
    public Ticket getTicketById(int id) {
        return list.stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst()
                .orElseThrow(() ->new RuntimeException("Ticket[id=" + id + "] is not found."));
    }

    @Override
    public List<Ticket> listTickets() {
        return new ArrayList<>(list);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        boolean isTicketExists = list.stream()
                .filter(t -> t.getOfferId() == ticket.getOfferId()
                        && t.getPlace() == ticket.getPlace())
                .findFirst().isPresent();
        if(isTicketExists){
            throw new RuntimeException("Ticket with offer  '" + ticket.getOfferId() + "' and place"
                    + ticket.getPlace() + " already exists. Please, enter another name.");
        }

        list.add(ticket);
        return ticket;
    }

    @Override
    public Ticket updateTicket(int id, Ticket ticket) {
        boolean isDeleted = list.removeIf(t -> t.getId() == id);
        if (isDeleted) {
            list.add(ticket);
        } else {
            throw new RuntimeException("Offer is not found!");
        }
        return ticket;
    }

    @Override
    public void deleteTicket(int id) {
        list.removeIf(offer -> offer.getId() == id);
    }
}
