package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.HallDto;
import com.epam.homework4.cinemawebapp.mapper.HallMapper;
import com.epam.homework4.cinemawebapp.model.Hall;
import com.epam.homework4.cinemawebapp.service.HallService;
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
    private HallService hallService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long id = Long.parseLong("1");
    private final String name = "SomeHall";
    private final int size = 40;
    private final String schemeImageDir = "folder/scheme/image.img";

    @Test
    void getHallByIdTest() throws Exception{
        //given
        Hall hall = new Hall();
        hall.setName(name);
        hall.setId(id);

        when(hallService.getById(id)).thenReturn(hall);

        //when
        mockMvc.perform(get("/hall/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(hall.getName()));

        //then
        verify(hallService, times(1)).getById(id);
    }

    @Test
    void getAllHallsTest() throws Exception{
        //given
        Hall hall = new Hall();
        hall.setName(name);

        when(hallService.getAll()).thenReturn(Collections.singletonList(hall));

        //when
        mockMvc.perform(get("/hall"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(hall.getName()));

        //then
        verify(hallService, times(1)).getAll();
    }

    @Test
    void createHallTest() throws Exception{
        //given
        HallDto requestBodyHallDtoToCreate = new HallDto();
        requestBodyHallDtoToCreate.setName(name);
        requestBodyHallDtoToCreate.setSize(size);
        requestBodyHallDtoToCreate.setSchemeImageDir(schemeImageDir);

        Hall createdHall = HallMapper.INSTANCE.mapHall(requestBodyHallDtoToCreate);

        when(hallService.create(createdHall)).thenReturn(createdHall);

        //when
        mockMvc.perform(post("/hall")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyHallDtoToCreate)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(createdHall.getName()));

        //then
        verify(hallService, times(1)).create(createdHall);
    }

    @Test
    void updateHallTest() throws Exception{
        //given
        HallDto requestBodyHallDtoToUpdate = new HallDto();
        requestBodyHallDtoToUpdate.setSize(45);
        requestBodyHallDtoToUpdate.setName("SomeHall2");
        requestBodyHallDtoToUpdate.setSchemeImageDir("folder/scheme/image.img");

        Hall updatedHall = HallMapper.INSTANCE.mapHall(requestBodyHallDtoToUpdate);

        when(hallService.update(id, updatedHall)).thenReturn(updatedHall);

        //when
        mockMvc.perform(put("/hall/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyHallDtoToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(updatedHall.getName()));

        //then
        verify(hallService, times(1)).update(id, updatedHall);
    }

    @Test
    void deleteHallTest() throws Exception{
        //given
        doNothing().when(hallService).delete(id);

        //when
        mockMvc.perform(delete("/hall/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(hallService, times(1)).delete(id);
    }
}
