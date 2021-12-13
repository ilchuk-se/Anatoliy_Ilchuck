package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Hall;
import com.epam.homework4.cinemawebapp.repository.HallRepository;
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
class HallServiceImplTest {
    @InjectMocks
    private HallServiceImpl hallService;

    @Mock
    private HallRepository hallRepository;

    private final Long id = Long.parseLong("1");
    private final String name = "someHall";
    private final int size = 40;

    @Test
    void getHallByIdTest(){
        //given
        Hall expectedHall = new Hall();
        expectedHall.setId(id);
        Optional<Hall> expectedHallOptional = Optional.of(expectedHall);

        when(hallRepository.findById(id)).thenReturn(expectedHallOptional);

        //when
        Hall actualHall = hallService.getById(id);

        //then
        assertEquals(actualHall, expectedHall);
        verify(hallRepository, times(1)).findById(id);
    }

    @Test
    void listHallsTest(){
        //given
        Hall hall = new Hall();
        hall.setId(id);

        List<Hall> expectedHalls = new ArrayList<>();
        expectedHalls.add(hall);

        hall.setId(Long.parseLong("2"));
        expectedHalls.add(hall);

        Iterable<Hall> expectedHallIterable = expectedHalls;

        when(hallRepository.findAll()).thenReturn(expectedHallIterable);

        //when
        List<Hall> actualHalls = hallService.getAll();

        //then
        assertEquals(actualHalls, expectedHalls);
        verify(hallRepository, times(1)).findAll();
    }

    @Test
    void createHallTest(){
        //given
        Hall hallToCreate = new Hall();
        hallToCreate.setName(name);
        hallToCreate.setSize(size);

        when(hallRepository.save(hallToCreate)).thenReturn(hallToCreate);

        //when
        Hall createdHall = hallService.create(hallToCreate);

        //then
        verify(hallRepository, times(1)).save(hallToCreate);
        assertEquals(createdHall, hallToCreate);
    }

    @Test
    void updateHallTest(){
        //given
        Hall HallInDb = new Hall();
        HallInDb.setId(id);
        Optional<Hall> HallInDbOptional = Optional.of(HallInDb);

        when(hallRepository.findById(id)).thenReturn(HallInDbOptional);

        Hall dataToUpdate = new Hall();
        dataToUpdate.setName("newName");

        Hall updatedHall = new Hall();
        updatedHall.setId(id);
        updatedHall.setName("newName");;

        when(hallRepository.save(updatedHall)).thenReturn(updatedHall);

        //when
        Hall createdHall = hallService.update(id, dataToUpdate);

        //then
        verify(hallRepository, times(1)).save(updatedHall);
    }

    @Test
    void updateHallTest_WhenHallNotExist(){
        //given
        when(hallRepository.findById(id)).thenReturn(Optional.empty());
        Hall dataToUpdate = new Hall();

        //then
        assertThrows(NoSuchElementException.class, () -> hallService.update(id, dataToUpdate));
    }

    @Test
    void deleteHallTest(){
        //given
        doNothing().when(hallRepository).deleteById(id);

        //when
        hallService.delete(id);

        //then
        verify(hallRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteHall_WithExceptionTest() {
        doThrow(RuntimeException.class).when(hallRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> hallService.delete(id));
    }
}
