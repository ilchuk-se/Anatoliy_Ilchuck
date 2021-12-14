package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Offer;
import com.epam.homework4.cinemawebapp.repository.OfferRepository;
import com.epam.homework4.cinemawebapp.service.impl.OfferServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class OfferServiceTest {
    @InjectMocks
    private OfferServiceImpl OfferService;

    @Mock
    private OfferRepository OfferRepository;

    private final Long id = Long.parseLong("1");
    private final Date date = new Date(13);
    private final Time time = new Time(14);

    @Test
    void getOfferByIdTest(){
        //given
        Offer expectedOffer = new Offer();
        expectedOffer.setId(id);
        Optional<Offer> expectedOfferOptional = Optional.of(expectedOffer);

        when(OfferRepository.findById(id)).thenReturn(expectedOfferOptional);

        //when
        Offer actualOffer = OfferService.getById(id);

        //then
        assertEquals(actualOffer, expectedOffer);
        verify(OfferRepository, times(1)).findById(id);
    }

    @Test
    void listOffersTest(){
        //given
        Offer Offer = new Offer();
        Offer.setId(id);

        List<Offer> expectedOffers = new ArrayList<>();
        expectedOffers.add(Offer);

        Offer.setId(Long.parseLong("2"));
        expectedOffers.add(Offer);

        Iterable<Offer> expectedOfferIterable = expectedOffers;

        when(OfferRepository.findAll()).thenReturn(expectedOfferIterable);

        //when
        List<Offer> actualOffers = OfferService.getAll();

        //then
        assertEquals(actualOffers, expectedOffers);
        verify(OfferRepository, times(1)).findAll();
    }

    @Test
    void createOfferTest(){
        //given
        Offer OfferToCreate = new Offer();
        OfferToCreate.setDate(date);
        OfferToCreate.setTime(time);

        when(OfferRepository.save(OfferToCreate)).thenReturn(OfferToCreate);

        //when
        Offer createdOffer = OfferService.create(OfferToCreate);

        //then
        verify(OfferRepository, times(1)).save(OfferToCreate);
        assertEquals(createdOffer, OfferToCreate);
    }

    @Test
    void deleteOfferTest(){
        //given
        doNothing().when(OfferRepository).deleteById(id);

        //when
        OfferService.delete(id);

        //then
        verify(OfferRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteOffer_WithExceptionTest() {
        doThrow(RuntimeException.class).when(OfferRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> OfferService.delete(id));
    }
}
