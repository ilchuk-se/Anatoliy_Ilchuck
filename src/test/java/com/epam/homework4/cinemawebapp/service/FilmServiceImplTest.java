package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.repository.FilmRepository;
import com.epam.homework4.cinemawebapp.service.impl.FilmServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {
    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    private final Long id = Long.parseLong("1");
    private final String name = "someFilm";
    private final String description = "someDescription";

    @Test
    void getFilmByIdTest(){
        //given
        Film expectedFilm = new Film();
        expectedFilm.setId(id);
        Optional<Film> expectedFilmOptional = Optional.of(expectedFilm);

        when(filmRepository.findById(id)).thenReturn(expectedFilmOptional);

        //when
        Film actualFilm = filmService.getById(id);

        //then
        assertEquals(actualFilm, expectedFilm);
        verify(filmRepository, times(1)).findById(id);
    }

    @Test
    void listFilmsTest(){
        //given
        Film Film = new Film();
        Film.setId(id);

        List<Film> expectedFilms = new ArrayList<>();
        expectedFilms.add(Film);

        Film.setId(Long.parseLong("2"));
        expectedFilms.add(Film);

        Iterable<Film> expectedFilmIterable = expectedFilms;

        when(filmRepository.findAll()).thenReturn(expectedFilmIterable);

        //when
        List<Film> actualFilms = filmService.getAll();

        //then
        assertEquals(actualFilms, expectedFilms);
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    void createFilmTest(){
        //given
        Film FilmToCreate = new Film();
        FilmToCreate.setLocalizedName(name);
        FilmToCreate.setDescription(description);

        when(filmRepository.save(FilmToCreate)).thenReturn(FilmToCreate);

        //when
        Film createdFilm = filmService.create(FilmToCreate);

        //then
        verify(filmRepository, times(1)).save(FilmToCreate);
        assertEquals(createdFilm, FilmToCreate);
    }

    @Test
    void updateFilmTest(){
        //given
        Film FilmInDb = new Film();
        FilmInDb.setId(id);
        Optional<Film> FilmInDbOptional = Optional.of(FilmInDb);

        when(filmRepository.findById(id)).thenReturn(FilmInDbOptional);

        Film dataToUpdate = new Film();
        dataToUpdate.setLocalizedName("newName");

        Film updatedFilm = new Film();
        updatedFilm.setId(id);
        updatedFilm.setLocalizedName("newName");;

        when(filmRepository.save(updatedFilm)).thenReturn(updatedFilm);

        //when
        Film createdFilm = filmService.update(id, dataToUpdate);

        //then
        verify(filmRepository, times(1)).save(updatedFilm);
    }

    @Test
    void updateFilmTest_WhenFilmNotExist(){
        //given
        when(filmRepository.findById(id)).thenReturn(Optional.empty());
        Film dataToUpdate = new Film();

        //then
        assertThrows(NoSuchElementException.class, () -> filmService.update(id, dataToUpdate));
    }

    @Test
    void deleteFilmTest(){
        //given
        doNothing().when(filmRepository).deleteById(id);

        //when
        filmService.delete(id);

        //then
        verify(filmRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteFilm_WithExceptionTest() {
        doThrow(RuntimeException.class).when(filmRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> filmService.delete(id));
    }
}
