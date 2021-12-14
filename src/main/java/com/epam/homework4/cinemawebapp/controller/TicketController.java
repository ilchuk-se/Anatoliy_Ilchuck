package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.TicketDto;
import com.epam.homework4.cinemawebapp.mapper.TicketMapper;
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
public final class TicketController{

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
    public TicketDto getById(@PathVariable final Long id) {
        return TicketMapper.INSTANCE.mapTicketDto(
                ticketService.getById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/ticket")
    public TicketDto create(@RequestBody @Valid final TicketDto ticketDto){
        return  TicketMapper.INSTANCE.mapTicketDto(
                ticketService.create(TicketMapper.INSTANCE.mapTicket(ticketDto))
        );
    }

    @DeleteMapping(value = "/ticket/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
