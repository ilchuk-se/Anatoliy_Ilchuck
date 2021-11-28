package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.service.IFilmService;
import com.epam.homework4.cinemawebapp.test.config.TestWebConfig;
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
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFilmService FilmService;

    private final Long id = Long.parseLong("1");
    private final String name = "SomeFilm";
    private final String description = "SomeDescription";

    @Test
    void getFilmByIdTest() throws Exception{
        //given
        Film film = new Film();
        film.setLocalizedName(name);
        film.setId(id);

        when(FilmService.getFilmById(id)).thenReturn(film);

        //when
        mockMvc.perform(get("/film/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.localizedName").value(film.getLocalizedName()));

        //then
        verify(FilmService, times(1)).getFilmById(id);
    }

    @Test
    void getAllFilmsTest() throws Exception{
        //given
        Film film = new Film();
        film.setLocalizedName(name);

        when(FilmService.listFilms()).thenReturn(Collections.singletonList(film));

        //when
        mockMvc.perform(get("/film"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].localizedName").value(film.getLocalizedName()));

        //then
        verify(FilmService, times(1)).listFilms();
    }

    @Test
    void createFilmTest() throws Exception{
        //given
        Film filmToCreate = new Film();
        filmToCreate.setId(id);
        filmToCreate.setLocalizedName("якийсь фільм");
        filmToCreate.setLocalizedName("someFilm");
        filmToCreate.setDescription("someDescription");
        filmToCreate.setImdbRating(7);
        filmToCreate.setTimekeeping(Time.valueOf("1:30:00"));
        filmToCreate.setPosterImageDir("folder/img/some.img");

        when(FilmService.createFilm(filmToCreate)).thenReturn(filmToCreate);

        String requestBodyJson =
                "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"originalName\": \"якийсь фільм\",\n" +
                "    \"localizedName\": \"someFilm\",\n" +
                "    \"description\": \"someDescription\",\n" +
                "    \"imdbRating\": \"7.0\",\n" +
                "    \"timekeeping\": \"1:30:00\",\n" +
                "    \"posterImageDir\": \"folder/img/some.img\"\n" +
                "}";

        //when
        mockMvc.perform(post("/film" ).contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.localizedName").value(filmToCreate.getLocalizedName()));

        //then
        verify(FilmService, times(1)).createFilm(filmToCreate);
    }

    @Test
    void updateFilmTest() throws Exception{
        //given
        Film filmToUpdate = new Film();
        filmToUpdate.setId(id);
        filmToUpdate.setLocalizedName("якийсь новий фільм");
        filmToUpdate.setLocalizedName("newSomeFilm");
        filmToUpdate.setDescription("someDescription");
        filmToUpdate.setImdbRating(9);
        filmToUpdate.setTimekeeping(Time.valueOf("2:30:00"));
        filmToUpdate.setPosterImageDir("folder/img/newSome.img");

        when(FilmService.updateFilm(id, filmToUpdate)).thenReturn(filmToUpdate);

        String requestBodyJson =
                "{\n" +
                "    \"id\": \"0\",\n" +
                "    \"originalName\": \"якийсь новий фільм\",\n" +
                "    \"localizedName\": \"newSomeFilm\",\n" +
                "    \"description\": \"someDescription\",\n" +
                "    \"imdbRating\": \"9.0\",\n" +
                "    \"timekeeping\": \"2:30:00\",\n" +
                "    \"posterImageDir\": \"folder/img/newSome.img\"\n" +
                "}";
        //when
        mockMvc.perform(put("/film/" + id).contentType(MediaType.APPLICATION_JSON).content(requestBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.localizedName").value(filmToUpdate.getLocalizedName()));

        //then
        verify(FilmService, times(1)).updateFilm(id, filmToUpdate);
    }

    @Test
    void deleteFilmTest() throws Exception{
        //given
        doNothing().when(FilmService).deleteFilm(id);

        //when
        mockMvc.perform(delete("/film/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(FilmService, times(1)).deleteFilm(id);
    }
}
