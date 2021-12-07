package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.TicketDto;
import com.epam.homework4.cinemawebapp.mapper.TicketMapper;
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
    public List<TicketDto> getAllTickets() {
        return TicketMapper.INSTANCE.mapTicketDtos(
                ticketService.listTickets()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/ticket/{id}")
    public TicketDto getTicketById(@PathVariable Long id) {
        return TicketMapper.INSTANCE.mapTicketDto(
                ticketService.getTicketById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/ticket")
    public TicketDto createTicket(@RequestBody @Valid TicketDto ticketDto){
        Ticket ticket = TicketMapper.INSTANCE.mapTicket(ticketDto);
        return  TicketMapper.INSTANCE.mapTicketDto(
                ticketService.createTicket(ticket)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/ticket/{id}")
    public TicketDto updateTicket(@PathVariable Long id, @RequestBody @Valid TicketDto ticketDto)  {
        Ticket ticketDataToUpdate = TicketMapper.INSTANCE.mapTicket(ticketDto);
        return TicketMapper.INSTANCE.mapTicketDto(
                ticketService.updateTicket(id, ticketDataToUpdate)
        );
    }

    @DeleteMapping(value = "/ticket/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
