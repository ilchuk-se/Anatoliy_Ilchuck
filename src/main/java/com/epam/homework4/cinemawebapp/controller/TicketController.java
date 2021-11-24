package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.exception.TicketControllerException;
import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.service.ITicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TicketController{

    private final ITicketService ticketService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/ticket")
    public List<Ticket> getAllTickets() {
        return  ticketService.listTickets();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/ticket/{id}")
    public Ticket getTicketById(@PathVariable String id) throws TicketControllerException {
        Ticket Ticket = null;
        try {
            Long TicketId = Long.parseLong(id);
            Ticket = ticketService.getTicketById(TicketId);
        }catch (NumberFormatException ex){
            String message = "Can not cast Ticket id to int";
            log.info(message);
            throw new TicketControllerException(message, ex.getCause());
        }
        return Ticket;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/ticket")
    public Ticket createTicket(@RequestBody @Valid Ticket Ticket){
        return  ticketService.createTicket(Ticket);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/ticket/{id}")
    public Ticket updateTicket(@PathVariable String id, @RequestBody @Valid Ticket Ticket) throws TicketControllerException {
        Ticket TicketUpdated = null;
        try {
            Long TicketId = Long.parseLong(id);
            TicketUpdated = ticketService.updateTicket(TicketId, Ticket);
        }catch (NumberFormatException ex){
            String message = "Can not cast Ticket id to int";
            log.info(message);
            throw new TicketControllerException(message, ex.getCause());
        }
        return TicketUpdated;
    }

    @DeleteMapping(value = "/ticket/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) throws TicketControllerException {
        try {
            Long TicketId = Long.parseLong(id);
            ticketService.deleteTicket(TicketId);
        } catch (NumberFormatException ex) {
            String message = "Can not cast Ticket id to int";
            log.info(message);
            throw new TicketControllerException(message, ex.getCause());
        }

        return ResponseEntity.noContent().build();
    }
}
