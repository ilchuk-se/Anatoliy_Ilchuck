package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.TicketDto;
import com.epam.homework4.cinemawebapp.mapper.TicketMapper;
import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.service.TicketService;
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

    private final TicketService ticketService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/ticket")
    public List<TicketDto> getAll() {
        return TicketMapper.INSTANCE.mapTicketDtos(
                ticketService.getAll()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/ticket/{id}")
    public TicketDto getById(@PathVariable Long id) {
        return TicketMapper.INSTANCE.mapTicketDto(
                ticketService.getById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/ticket")
    public TicketDto create(@RequestBody @Valid TicketDto ticketDto){
        Ticket ticketToCreate = TicketMapper.INSTANCE.mapTicket(ticketDto);
        return  TicketMapper.INSTANCE.mapTicketDto(
                ticketService.create(ticketToCreate)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/ticket/{id}")
    public TicketDto update(@PathVariable Long id, @RequestBody @Valid TicketDto ticketDto)  {
        Ticket ticketToUpdate = TicketMapper.INSTANCE.mapTicket(ticketDto);
        return TicketMapper.INSTANCE.mapTicketDto(
                ticketService.update(id, ticketToUpdate)
        );
    }

    @DeleteMapping(value = "/ticket/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
