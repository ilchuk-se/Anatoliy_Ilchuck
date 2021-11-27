package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.repository.IHallRepository;
import com.epam.homework4.cinemawebapp.service.impl.HallServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HallServiceImplTest {
    @InjectMocks
    private HallServiceImpl hallService;

    @Mock
    private IHallRepository hallRepository;

    private final Long id = Long.parseLong("1");
    private final String name = "someHall";
    private final int size = 40;

    @Test
    void getHallByIdTest(){
        //given
        CinemaHall expectedHall = new CinemaHall();
        expectedHall.setId(id);
        Optional<CinemaHall> expectedHallOptional = Optional.of(expectedHall);

        when(hallRepository.findById(id)).thenReturn(expectedHallOptional);

        //when
        CinemaHall actualHall = hallService.getHallById(id);

        //then
        assertEquals(actualHall, expectedHall);
        verify(hallRepository, times(1)).findById(id);
    }

    @Test
    void listHallsTest(){
        //given
        CinemaHall hall = new CinemaHall();
        hall.setId(id);

        List<CinemaHall> expectedHalls = new ArrayList<>();
        expectedHalls.add(hall);

        hall.setId(Long.parseLong("2"));
        expectedHalls.add(hall);

        Iterable<CinemaHall> expectedHallIterable = expectedHalls;

        when(hallRepository.findAll()).thenReturn(expectedHallIterable);

        //when
        List<CinemaHall> actualHalls = hallService.listHalls();

        //then
        assertEquals(actualHalls, expectedHalls);
        verify(hallRepository, times(1)).findAll();
    }

    @Test
    void createHallTest(){
        //given
        CinemaHall hallToCreate = new CinemaHall();
        hallToCreate.setName(name);
        hallToCreate.setSize(size);

        when(hallRepository.save(hallToCreate)).thenReturn(hallToCreate);

        //when
        CinemaHall createdHall = hallService.createHall(hallToCreate);

        //then
        verify(hallRepository, times(1)).save(hallToCreate);
        assertEquals(createdHall, hallToCreate);
    }

    @Test
    void updateHallTest(){
        //given
        CinemaHall HallInDb = new CinemaHall();
        HallInDb.setId(id);
        Optional<CinemaHall> HallInDbOptional = Optional.of(HallInDb);

        when(hallRepository.findById(id)).thenReturn(HallInDbOptional);

        CinemaHall dataToUpdate = new CinemaHall();
        dataToUpdate.setName("newName");

        CinemaHall updatedHall = new CinemaHall();
        updatedHall.setId(id);
        updatedHall.setName("newName");;

        when(hallRepository.save(updatedHall)).thenReturn(updatedHall);

        //when
        CinemaHall createdHall = hallService.updateHall(id, dataToUpdate);

        //then
        verify(hallRepository, times(1)).save(updatedHall);
    }

    @Test
    void updateHallTest_WhenHallNotExist(){
        //given
        when(hallRepository.findById(id)).thenReturn(Optional.empty());
        CinemaHall dataToUpdate = new CinemaHall();

        //then
        assertThrows(NoSuchElementException.class, () -> hallService.updateHall(id, dataToUpdate));
    }

    @Test
    void deleteHallTest(){
        //given
        doNothing().when(hallRepository).deleteById(id);

        //when
        hallService.deleteHall(id);

        //then
        verify(hallRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteHall_WithExceptionTest() {
        doThrow(RuntimeException.class).when(hallRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> hallService.deleteHall(id));
    }
}
