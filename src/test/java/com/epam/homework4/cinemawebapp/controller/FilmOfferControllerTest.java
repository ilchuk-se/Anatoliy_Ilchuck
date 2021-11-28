package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.model.FilmOffer;
import com.epam.homework4.cinemawebapp.service.IFilmOfferService;
import com.epam.homework4.cinemawebapp.test.config.TestWebConfig;
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
public class FilmOfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFilmOfferService filmOfferService;

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
        FilmOffer filmOfferToCreate = new FilmOffer();
        filmOfferToCreate.setId(id);
        filmOfferToCreate.setDate(date);
        filmOfferToCreate.setTime(Time.valueOf("17:00:00"));

        when(filmOfferService.createFilmOffer(filmOfferToCreate)).thenReturn(filmOfferToCreate);

        String requestBodyJson =
                "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"date\": \"2023-12-22\",\n" +
                "    \"time\": \"17:00:00\",\n" +
                "    \"price\": \"140\",\n" +
                "    \"film\": {\n" +
                "        \"id\" : \"1\"\n" +
                "    },\n" +
                "    \"hallId\": \"0\"\n" +
                "}";

        //when
        mockMvc.perform(post("/offer" ).contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(filmOfferToCreate.getDate().toString()));

        //then
        verify(filmOfferService, times(1)).createFilmOffer(filmOfferToCreate);
    }

    @Test
    void updateFilmOfferTest() throws Exception{
        //given
        FilmOffer filmOfferToUpdate = new FilmOffer();
        filmOfferToUpdate.setId(id);
        filmOfferToUpdate.setDate(date);
        filmOfferToUpdate.setTime(Time.valueOf("17:00:00"));

        when(filmOfferService.updateFilmOffer(id, filmOfferToUpdate)).thenReturn(filmOfferToUpdate);

        String requestBodyJson =
                "{\n" +
                "    \"id\": \"0\",\n" +
                "    \"date\": \"2011-11-11\",\n" +
                "    \"time\": \"11:00:00\",\n" +
                "    \"price\": \"110\",\n" +
                "    \"filmId\": \"1\",\n" +
                "    \"hallId\": \"1\"\n" +
                "}";
        //when
        mockMvc.perform(put("/offer/" + id).contentType(MediaType.APPLICATION_JSON).content(requestBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.localizedName").value(filmOfferToUpdate.getDate().toString()));

        //then
        verify(filmOfferService, times(1)).updateFilmOffer(id, filmOfferToUpdate);
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
