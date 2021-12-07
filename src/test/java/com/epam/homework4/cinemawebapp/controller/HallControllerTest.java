package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.CinemaHallDto;
import com.epam.homework4.cinemawebapp.mapper.CinemaHallMapper;
import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.service.IHallService;
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

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = HallController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class HallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IHallService hallService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long id = Long.parseLong("1");
    private final String name = "SomeHall";
    private final int size = 40;
    private final String schemeImageDir = "folder/scheme/image.img";

    @Test
    void getHallByIdTest() throws Exception{
        //given
        CinemaHall hall = new CinemaHall();
        hall.setName(name);
        hall.setId(id);

        when(hallService.getHallById(id)).thenReturn(hall);

        //when
        mockMvc.perform(get("/hall/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(hall.getName()));

        //then
        verify(hallService, times(1)).getHallById(id);
    }

    @Test
    void getAllHallsTest() throws Exception{
        //given
        CinemaHall hall = new CinemaHall();
        hall.setName(name);

        when(hallService.listHalls()).thenReturn(Collections.singletonList(hall));

        //when
        mockMvc.perform(get("/hall"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(hall.getName()));

        //then
        verify(hallService, times(1)).listHalls();
    }

    @Test
    void createHallTest() throws Exception{
        //given
        CinemaHallDto requestBodyHallDtoToCreate = new CinemaHallDto();
        requestBodyHallDtoToCreate.setName(name);
        requestBodyHallDtoToCreate.setSize(size);
        requestBodyHallDtoToCreate.setSchemeImageDir(schemeImageDir);

        CinemaHall createdHall = CinemaHallMapper.INSTANCE.mapCinemaHall(requestBodyHallDtoToCreate);

        when(hallService.createHall(createdHall)).thenReturn(createdHall);

        //when
        mockMvc.perform(post("/hall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyHallDtoToCreate)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(createdHall.getName()));

        //then
        verify(hallService, times(1)).createHall(createdHall);
    }

    @Test
    void updateHallTest() throws Exception{
        //given
        CinemaHallDto requestBodyHallDtoToUpdate = new CinemaHallDto();
        requestBodyHallDtoToUpdate.setSize(45);
        requestBodyHallDtoToUpdate.setName("SomeHall2");
        requestBodyHallDtoToUpdate.setSchemeImageDir("folder/scheme/image.img");

        CinemaHall updatedHall = CinemaHallMapper.INSTANCE.mapCinemaHall(requestBodyHallDtoToUpdate);

        when(hallService.updateHall(id, updatedHall)).thenReturn(updatedHall);

        //when
        mockMvc.perform(put("/hall/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyHallDtoToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(updatedHall.getName()));

        //then
        verify(hallService, times(1)).updateHall(id, updatedHall);
    }

    @Test
    void deleteHallTest() throws Exception{
        //given
        doNothing().when(hallService).deleteHall(id);

        //when
        mockMvc.perform(delete("/hall/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(hallService, times(1)).deleteHall(id);
    }
}
