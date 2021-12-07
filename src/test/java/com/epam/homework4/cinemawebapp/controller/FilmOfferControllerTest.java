package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.FilmDto;
import com.epam.homework4.cinemawebapp.dto.FilmOfferDto;
import com.epam.homework4.cinemawebapp.mapper.FilmOfferMapper;
import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.service.IFilmOfferService;
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

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = FilmOfferController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class FilmOfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFilmOfferService filmOfferService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long id = Long.parseLong("1");
    private final Date date = Date.valueOf("2023-12-22");

    @Test
    void getFilmOfferByIdTest() throws Exception{
        //given
        FilmOffer filmOffer = new FilmOffer();
        filmOffer.setDate(date);

        when(filmOfferService.getFilmOfferById(id)).thenReturn(filmOffer);

        //when
        mockMvc.perform(get("/offer/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(filmOffer.getDate().toString()));

        //then
        verify(filmOfferService, times(1)).getFilmOfferById(id);
    }

    @Test
    void getAllFilmOffersTest() throws Exception{
        //given
        FilmOffer filmOffer = new FilmOffer();
        filmOffer.setDate(date);

        when(filmOfferService.listFilmOffers()).thenReturn(Collections.singletonList(filmOffer));

        //when
        mockMvc.perform(get("/offer"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].date").value(filmOffer.getDate().toString()));

        //then
        verify(filmOfferService, times(1)).listFilmOffers();
    }

    @Test
    void createFilmOfferTest() throws Exception{
        //given
        FilmOfferDto requestBodyFilmOfferDtoToCreate = new FilmOfferDto();
        requestBodyFilmOfferDtoToCreate.setDate(date);
        requestBodyFilmOfferDtoToCreate.setTime(Time.valueOf("17:00:00"));

        FilmOffer createdFilmOffer = FilmOfferMapper.INSTANCE.mapFilmOffer(requestBodyFilmOfferDtoToCreate);

        when(filmOfferService.createFilmOffer(createdFilmOffer)).thenReturn(createdFilmOffer);

        //when
        mockMvc.perform(post("/offer" ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyFilmOfferDtoToCreate)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(createdFilmOffer.getDate().toString()));

        //then
        verify(filmOfferService, times(1)).createFilmOffer(createdFilmOffer);
    }

    @Test
    void updateFilmOfferTest() throws Exception{
        //given
        FilmOfferDto requestBodyFilmOfferDtoToUpdate = new FilmOfferDto();
        requestBodyFilmOfferDtoToUpdate.setDate(date);
        requestBodyFilmOfferDtoToUpdate.setTime(Time.valueOf("17:00:00"));

        FilmDto filmDto = new FilmDto();
        filmDto.setId(id);
        requestBodyFilmOfferDtoToUpdate.setFilm(filmDto);

        FilmOffer updatedFilmOffer = FilmOfferMapper.INSTANCE.mapFilmOffer(requestBodyFilmOfferDtoToUpdate);

        when(filmOfferService.updateFilmOffer(id, updatedFilmOffer)).thenReturn(updatedFilmOffer);

        //when
        mockMvc.perform(put("/offer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyFilmOfferDtoToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(updatedFilmOffer.getDate().toString()));

        //then
        verify(filmOfferService, times(1)).updateFilmOffer(id, updatedFilmOffer);
    }

    @Test
    void deleteFilmOfferTest() throws Exception{
        //given
        doNothing().when(filmOfferService).deleteFilmOffer(id);

        //when
        mockMvc.perform(delete("/offer/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(filmOfferService, times(1)).deleteFilmOffer(id);
    }
}
