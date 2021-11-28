package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.service.ITicketService;
import com.epam.homework4.cinemawebapp.test.config.TestWebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = TicketController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITicketService ticketService;

    private final Long id = Long.parseLong("1");

    @Test
    void getTicketByIdTest() throws Exception{
        //given
        Ticket ticket = new Ticket();
        ticket.setId(id);

        when(ticketService.getTicketById(id)).thenReturn(ticket);

        //when
        mockMvc.perform(get("/ticket/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ticket.getId().toString()));

        //then
        verify(ticketService, times(1)).getTicketById(id);
    }

    @Test
    void getAllTicketsTest() throws Exception{
        //given
        Ticket ticket = new Ticket();
        ticket.setId(id);

        when(ticketService.listTickets()).thenReturn(Collections.singletonList(ticket));

        //when
        mockMvc.perform(get("/ticket"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ticket.getId()));

        //then
        verify(ticketService, times(1)).listTickets();
    }

    @Test
    void createTicketTest() throws Exception{
        //given
        Ticket ticketToCreate = new Ticket();
        ticketToCreate.setPlace(10);

        when(ticketService.createTicket(ticketToCreate)).thenReturn(ticketToCreate);

        String requestBodyJson =
                "{\n" +
                "    \"id\": \"0\",\n" +
                "    \"userId\": \"0\",\n" +
                "    \"offerId\": \"0\",\n" +
                "    \"place\": \"10\",\n" +
                "    \"price\": \"140.0\"\n" +
                "}";

        //when
        mockMvc.perform(post("/ticket").contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.place").value(ticketToCreate.getPlace()));

        //then
        verify(ticketService, times(1)).createTicket(ticketToCreate);
    }

    @Test
    void updateTicketTest() throws Exception{
        //given
        Ticket TicketToUpdate = new Ticket();
        TicketToUpdate.setPlace(15);

        when(ticketService.updateTicket(id, TicketToUpdate)).thenReturn(TicketToUpdate);

        String requestBodyJson =
                "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"userId\": \"1\",\n" +
                "    \"offerId\": \"1\",\n" +
                "    \"place\": \"15\",\n" +
                "    \"price\": \"140.0\"\n" +
                "}";
        //when
        mockMvc.perform(put("/ticket/" + id).contentType(MediaType.APPLICATION_JSON).content(requestBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.place").value(TicketToUpdate.getPlace()));

        //then
        verify(ticketService, times(1)).updateTicket(id, TicketToUpdate);
    }

    @Test
    void deleteTicketTest() throws Exception{
        //given
        doNothing().when(ticketService).deleteTicket(id);

        //when
        mockMvc.perform(delete("/ticket/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(ticketService, times(1)).deleteTicket(id);
    }
}
