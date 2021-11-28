package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.service.IHallService;
import com.epam.homework4.cinemawebapp.test.config.TestWebConfig;
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
public class HallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IHallService hallService;

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
                .andExpect(jsonPath("$[0].name").value(hall.getName()));;

        //then
        verify(hallService, times(1)).listHalls();
    }

    @Test
    void createHallTest() throws Exception{
        //given
        CinemaHall hallToCreate = new CinemaHall();
        hallToCreate.setName(name);
        hallToCreate.setId(id);
        hallToCreate.setSize(size);
        hallToCreate.setSchemeImageDir(schemeImageDir);

        when(hallService.createHall(hallToCreate)).thenReturn(hallToCreate);

        String requestBodyJson =
                "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"size\": \"40\",\n" +
                "    \"schemeImageDir\": \"folder/scheme/image.img\",\n" +
                "    \"name\": \"SomeHall\"\n" +
                "}";

        //when
        mockMvc.perform(post("/hall").contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(hallToCreate.getName()));

        //then
        verify(hallService, times(1)).createHall(hallToCreate);
    }

    @Test
    void updateHallTest() throws Exception{
        //given
        CinemaHall hallToUpdate = new CinemaHall();
        hallToUpdate.setSize(45);
        hallToUpdate.setName("SomeHall2");
        hallToUpdate.setSchemeImageDir("folder/scheme/image.img");
        hallToUpdate.setId(id);

        when(hallService.updateHall(id, hallToUpdate)).thenReturn(hallToUpdate);

        String requestBodyJson =
                "{\n" + "\"id\": \"1\"," +
                "    \"size\": \"45\",\n" +
                "    \"schemeImageDir\": \"folder/scheme/image.img\",\n" +
                "    \"name\": \"SomeHall2\"\n" +
                "}";
        //when
        mockMvc.perform(put("/hall/" + id).contentType(MediaType.APPLICATION_JSON).content(requestBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(hallToUpdate.getName()));

        //then
        verify(hallService, times(1)).updateHall(id, hallToUpdate);
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
