package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.FilmDto;
import com.epam.homework4.cinemawebapp.mapper.FilmMapper;
import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.service.FilmService;
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

import java.sql.Time;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = FilmController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long id = Long.parseLong("1");
    private final String name = "SomeFilm";
    private final String description = "SomeDescription";

    @Test
    void getFilmByIdTest() throws Exception{
        //given
        Film film = new Film();
        film.setLocalizedName(name);
        film.setId(id);

        when(filmService.getById(id)).thenReturn(film);

        //when
        mockMvc.perform(get("/film/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.localizedName").value(film.getLocalizedName()));

        //then
        verify(filmService, times(1)).getById(id);
    }

    @Test
    void getAllFilmsTest() throws Exception{
        //given
        Film film = new Film();
        film.setLocalizedName(name);

        when(filmService.getAll()).thenReturn(Collections.singletonList(film));

        //when
        mockMvc.perform(get("/film"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].localizedName").value(film.getLocalizedName()));

        //then
        verify(filmService, times(1)).getAll();
    }

    @Test
    void createFilmTest() throws Exception{
        //given
        FilmDto requestBodyFilmToCreateDto = new FilmDto();
        requestBodyFilmToCreateDto.setLocalizedName("якийсь фільм");
        requestBodyFilmToCreateDto.setLocalizedName("someFilm");
        requestBodyFilmToCreateDto.setDescription("someDescription");
        requestBodyFilmToCreateDto.setImdbRating(7);
        requestBodyFilmToCreateDto.setTimekeeping(Time.valueOf("1:30:00"));
        requestBodyFilmToCreateDto.setPosterImageDir("folder/img/some.img");

        Film createdFilm = FilmMapper.INSTANCE.mapFilm(requestBodyFilmToCreateDto);

        when(filmService.create(createdFilm)).thenReturn(createdFilm);

        //when
        mockMvc
                .perform(post("/film" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyFilmToCreateDto)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.localizedName").value(createdFilm.getLocalizedName()));

        //then
        verify(filmService, times(1)).create(createdFilm);
    }

    @Test
    void updateFilmTest() throws Exception{
        //given
        FilmDto requestBodyFilmDtoToUpdate = new FilmDto();
        requestBodyFilmDtoToUpdate.setLocalizedName("якийсь новий фільм");
        requestBodyFilmDtoToUpdate.setOriginalName("newSomeFilm");
        requestBodyFilmDtoToUpdate.setDescription("someDescription");
        requestBodyFilmDtoToUpdate.setImdbRating(9);
        requestBodyFilmDtoToUpdate.setTimekeeping(Time.valueOf("2:30:00"));
        requestBodyFilmDtoToUpdate.setPosterImageDir("folder/img/newSome.img");

        Film updatedFilm = FilmMapper.INSTANCE.mapFilm(requestBodyFilmDtoToUpdate);

        when(filmService.update(id, updatedFilm)).thenReturn(updatedFilm);

        //when
        mockMvc.perform(put("/film/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestBodyFilmDtoToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.localizedName").value(updatedFilm.getLocalizedName()));

        //then
        verify(filmService, times(1)).update(id, updatedFilm);
    }

    @Test
    void deleteFilmTest() throws Exception{
        //given
        doNothing().when(filmService).delete(id);

        //when
        mockMvc.perform(delete("/film/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(filmService, times(1)).delete(id);
    }
}
