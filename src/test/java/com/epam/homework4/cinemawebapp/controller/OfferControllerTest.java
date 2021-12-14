package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.FilmDto;
import com.epam.homework4.cinemawebapp.dto.OfferDto;
import com.epam.homework4.cinemawebapp.mapper.OfferMapper;
import com.epam.homework4.cinemawebapp.model.Offer;
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

@WebMvcTest(value = OfferController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class OfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.epam.homework4.cinemawebapp.service.OfferService offerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long id = Long.parseLong("1");
    private final Date date = Date.valueOf("2023-12-22");

    @Test
    void getOfferByIdTest() throws Exception{
        //given
        Offer Offer = new Offer();
        Offer.setDate(date);

        when(offerService.getById(id)).thenReturn(Offer);

        //when
        mockMvc.perform(get("/offer/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(Offer.getDate().toString()));

        //then
        verify(offerService, times(1)).getById(id);
    }

    @Test
    void getAllOffersTest() throws Exception{
        //given
        Offer Offer = new Offer();
        Offer.setDate(date);

        when(offerService.getAll()).thenReturn(Collections.singletonList(Offer));

        //when
        mockMvc.perform(get("/offer"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].date").value(Offer.getDate().toString()));

        //then
        verify(offerService, times(1)).getAll();
    }

    @Test
    void createOfferTest() throws Exception{
        //given
        OfferDto requestBodyOfferDtoToCreate = new OfferDto();
        requestBodyOfferDtoToCreate.setDate(date);
        requestBodyOfferDtoToCreate.setTime(Time.valueOf("17:00:00"));

        Offer createdOffer = OfferMapper.INSTANCE.mapOffer(requestBodyOfferDtoToCreate);

        when(offerService.create(createdOffer)).thenReturn(createdOffer);

        //when
        mockMvc.perform(post("/offer" ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyOfferDtoToCreate)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(createdOffer.getDate().toString()));

        //then
        verify(offerService, times(1)).create(createdOffer);
    }

    @Test
    void deleteOfferTest() throws Exception{
        //given
        doNothing().when(offerService).delete(id);

        //when
        mockMvc.perform(delete("/offer/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(offerService, times(1)).delete(id);
    }
}
