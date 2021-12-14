package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.OfferDto;
import com.epam.homework4.cinemawebapp.dto.TicketDto;
import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.TicketMapper;
import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.service.TicketService;
import com.epam.homework4.cinemawebapp.test.config.TestWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = TicketController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long id = Long.parseLong("1");

    @Test
    void getTicketByIdTest() throws Exception{
        //given
        Ticket ticket = new Ticket();
        ticket.setId(id);

        when(ticketService.getById(id)).thenReturn(ticket);

        //when
        mockMvc.perform(get("/ticket/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ticket.getId().toString()));

        //then
        verify(ticketService, times(1)).getById(id);
    }

    @Test
    void getAllTicketsTest() throws Exception{
        //given
        Ticket ticket = new Ticket();
        ticket.setId(id);

        when(ticketService.getAll()).thenReturn(Collections.singletonList(ticket));

        //when
        mockMvc.perform(get("/ticket"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ticket.getId()));

        //then
        verify(ticketService, times(1)).getAll();
    }

    @Test
    void createTicketTest() throws Exception{
        //given
        TicketDto requestBodyTicketDtoToCreate = new TicketDto();
        requestBodyTicketDtoToCreate.setPlace(10);
        requestBodyTicketDtoToCreate.setOffer(new OfferDto());
        requestBodyTicketDtoToCreate.setUser(new UserDto());
        requestBodyTicketDtoToCreate.setPlace(10);
        requestBodyTicketDtoToCreate.setPrice(BigDecimal.ZERO);

        Ticket createdTicket = TicketMapper.INSTANCE.mapTicket(requestBodyTicketDtoToCreate);

        when(ticketService.create(createdTicket)).thenReturn(createdTicket);

        //when
        mockMvc.perform(post("/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyTicketDtoToCreate)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.place").value(createdTicket.getPlace()));

        //then
        verify(ticketService, times(1)).create(createdTicket);
    }

    @Test
    void deleteTicketTest() throws Exception{
        //given
        doNothing().when(ticketService).delete(id);

        //when
        mockMvc.perform(delete("/ticket/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(ticketService, times(1)).delete(id);
    }
}
