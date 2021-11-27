package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.repository.IFilmOfferRepository;
import com.epam.homework4.cinemawebapp.service.impl.FilmOfferServiceImpl;
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
public class FilmOfferServiceTest {
    @InjectMocks
    private FilmOfferServiceImpl filmOfferService;

    @Mock
    private IFilmOfferRepository filmOfferRepository;

    private final Long id = Long.parseLong("1");
    private final Date date = new Date(13);
    private final Time time = new Time(14);

    @Test
    void getFilmOfferByIdTest(){
        //given
        FilmOffer expectedFilmOffer = new FilmOffer();
        expectedFilmOffer.setId(id);
        Optional<FilmOffer> expectedFilmOfferOptional = Optional.of(expectedFilmOffer);

        when(filmOfferRepository.findById(id)).thenReturn(expectedFilmOfferOptional);

        //when
        FilmOffer actualFilmOffer = filmOfferService.getFilmOfferById(id);

        //then
        assertEquals(actualFilmOffer, expectedFilmOffer);
        verify(filmOfferRepository, times(1)).findById(id);
    }

    @Test
    void listFilmOffersTest(){
        //given
        FilmOffer FilmOffer = new FilmOffer();
        FilmOffer.setId(id);

        List<FilmOffer> expectedFilmOffers = new ArrayList<>();
        expectedFilmOffers.add(FilmOffer);

        FilmOffer.setId(Long.parseLong("2"));
        expectedFilmOffers.add(FilmOffer);

        Iterable<FilmOffer> expectedFilmOfferIterable = expectedFilmOffers;

        when(filmOfferRepository.findAll()).thenReturn(expectedFilmOfferIterable);

        //when
        List<FilmOffer> actualFilmOffers = filmOfferService.listFilmOffers();

        //then
        assertEquals(actualFilmOffers, expectedFilmOffers);
        verify(filmOfferRepository, times(1)).findAll();
    }

    @Test
    void createFilmOfferTest(){
        //given
        FilmOffer FilmOfferToCreate = new FilmOffer();
        FilmOfferToCreate.setDate(date);
        FilmOfferToCreate.setTime(time);

        when(filmOfferRepository.save(FilmOfferToCreate)).thenReturn(FilmOfferToCreate);

        //when
        FilmOffer createdFilmOffer = filmOfferService.createFilmOffer(FilmOfferToCreate);

        //then
        verify(filmOfferRepository, times(1)).save(FilmOfferToCreate);
        assertEquals(createdFilmOffer, FilmOfferToCreate);
    }

    @Test
    void updateFilmOfferTest(){
        //given
        FilmOffer FilmOfferInDb = new FilmOffer();
        FilmOfferInDb.setId(id);
        Optional<FilmOffer> FilmOfferInDbOptional = Optional.of(FilmOfferInDb);

        when(filmOfferRepository.findById(id)).thenReturn(FilmOfferInDbOptional);

        FilmOffer dataToUpdate = new FilmOffer();
        dataToUpdate.setDate(date);

        FilmOffer updatedFilmOffer = new FilmOffer();
        updatedFilmOffer.setId(id);
        updatedFilmOffer.setDate(date);;

        when(filmOfferRepository.save(updatedFilmOffer)).thenReturn(updatedFilmOffer);

        //when
        FilmOffer createdFilmOffer = filmOfferService.updateFilmOffer(id, dataToUpdate);

        //then
        verify(filmOfferRepository, times(1)).save(updatedFilmOffer);
    }

    @Test
    void updateFilmOfferTest_WhenFilmOfferNotExist(){
        //given
        when(filmOfferRepository.findById(id)).thenReturn(Optional.empty());
        FilmOffer dataToUpdate = new FilmOffer();

        //then
        assertThrows(NoSuchElementException.class, () -> filmOfferService.updateFilmOffer(id, dataToUpdate));
    }

    @Test
    void deleteFilmOfferTest(){
        //given
        doNothing().when(filmOfferRepository).deleteById(id);

        //when
        filmOfferService.deleteFilmOffer(id);

        //then
        verify(filmOfferRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteFilmOffer_WithExceptionTest() {
        doThrow(RuntimeException.class).when(filmOfferRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> filmOfferService.deleteFilmOffer(id));
    }
}
