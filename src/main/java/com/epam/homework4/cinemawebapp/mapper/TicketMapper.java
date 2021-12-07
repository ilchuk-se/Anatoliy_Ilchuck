package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.TicketDto;
import com.epam.homework4.cinemawebapp.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDto mapTicketDto(Ticket ticket);

    List<TicketDto> mapTicketDtos(List<Ticket> tickets);

    Ticket mapTicket(TicketDto ticketDto);
}
