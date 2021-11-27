package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Ticket;
import com.epam.homework4.cinemawebapp.repository.ITicketRepository;
import com.epam.homework4.cinemawebapp.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private ITicketRepository ticketRepository;

    private final Long id = Long.parseLong("1");
    private final int place = 1;
    private final BigDecimal price = new BigDecimal(100);

    @Test
    void getTicketByIdTest(){
        //given
        Ticket expectedTicket = new Ticket();
        expectedTicket.setId(id);
        Optional<Ticket> expectedTicketOptional = Optional.of(expectedTicket);

        when(ticketRepository.findById(id)).thenReturn(expectedTicketOptional);

        //when
        Ticket actualTicket = ticketService.getTicketById(id);

        //then
        assertEquals(actualTicket, expectedTicket);
        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    void listTicketsTest(){
        //given
        Ticket Ticket = new Ticket();
        Ticket.setId(id);

        List<Ticket> expectedTickets = new ArrayList<>();
        expectedTickets.add(Ticket);

        Ticket.setId(Long.parseLong("2"));
        expectedTickets.add(Ticket);

        Iterable<Ticket> expectedTicketIterable = expectedTickets;

        when(ticketRepository.findAll()).thenReturn(expectedTicketIterable);

        //when
        List<Ticket> actualTickets = ticketService.listTickets();

        //then
        assertEquals(actualTickets, expectedTickets);
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void createTicketTest(){
        //given
        Ticket TicketToCreate = new Ticket();
        TicketToCreate.setPlace(place);
        TicketToCreate.setPrice(price);

        when(ticketRepository.save(TicketToCreate)).thenReturn(TicketToCreate);

        //when
        Ticket createdTicket = ticketService.createTicket(TicketToCreate);

        //then
        verify(ticketRepository, times(1)).save(TicketToCreate);
        assertEquals(createdTicket, TicketToCreate);
    }

    @Test
    void updateTicketTest(){
        //given
        Ticket TicketInDb = new Ticket();
        TicketInDb.setId(id);
        Optional<Ticket> TicketInDbOptional = Optional.of(TicketInDb);

        when(ticketRepository.findById(id)).thenReturn(TicketInDbOptional);

        Ticket dataToUpdate = new Ticket();
        dataToUpdate.setPlace(place);

        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(id);
        updatedTicket.setPlace(place);

        when(ticketRepository.save(updatedTicket)).thenReturn(updatedTicket);

        //when
        Ticket createdTicket = ticketService.updateTicket(id, dataToUpdate);

        //then
        verify(ticketRepository, times(1)).save(updatedTicket);
    }

    @Test
    void updateTicketTest_WhenTicketNotExist(){
        //given
        when(ticketRepository.findById(id)).thenReturn(Optional.empty());
        Ticket dataToUpdate = new Ticket();

        //then
        assertThrows(NoSuchElementException.class, () -> ticketService.updateTicket(id, dataToUpdate));
    }

    @Test
    void deleteTicketTest(){
        //given
        doNothing().when(ticketRepository).deleteById(id);

        //when
        ticketService.deleteTicket(id);

        //then
        verify(ticketRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteTicket_WithExceptionTest() {
        doThrow(RuntimeException.class).when(ticketRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> ticketService.deleteTicket(id));
    }
}
