package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.UserAuthDto;
import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.service.UserService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long id = Long.parseLong("1");
    private final String password = "ae146jj2843h123";

    @Test
    void getUserByIdTest() throws Exception{
        //given
        User userInDb = new User();
        userInDb.setId(id);

        when(userService.getById(id)).thenReturn(userInDb);

        //when
        mockMvc.perform(get("/user/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userInDb.getId().toString()));

        //then
        verify(userService, times(1)).getById(id);
    }

    @Test
    void userAuthTest() throws Exception{
        //given
        final String login = "some@gmail.com";
        UserAuthDto requestBodyUserAuthDto = new UserAuthDto(login, password);

        User userInDb = new User();
        userInDb.setId(id);
        userInDb.setPassword(password);
        userInDb.setLogin(login);

        when(userService.getAuthorized(login, password)).thenReturn(userInDb);

        //when
        mockMvc.perform(get("/sign").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyUserAuthDto)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(requestBodyUserAuthDto.getLogin()));
    }

    @Test
    void getAllUsersTest() throws Exception{
        //given
        User userInDb = new User();
        userInDb.setId(id);

        when(userService.getAll()).thenReturn(Collections.singletonList(userInDb));

        //when
        mockMvc.perform(get("/user"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(userInDb.getId().toString()));

        //then
        verify(userService, times(1)).getAll();
    }

    @Test
    void createUserTest() throws Exception{
        //given
        User userToCreate = new User();
        userToCreate.setLogin("some@mail.com");
        userToCreate.setPassword(password);
        userToCreate.setId(null);
        userToCreate.setName("userName");
        userToCreate.setRoleId(1);
        userToCreate.setRoleName("user");

        UserDto requestBodyUserDto = UserMapper.INSTANCE.mapUserDto(userToCreate);

        when(userService.create(userToCreate)).thenReturn(userToCreate);

        //when
        mockMvc.perform(post("/user?password=" + password).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBodyUserDto)))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(userToCreate.getName()));

        //then
        verify(userService, times(1)).create(userToCreate);
    }

    @Test
    void updateUserTest() throws Exception{
        //given
        User userToUpdate = new User();
        userToUpdate.setLogin("newSome@mail.com");
        userToUpdate.setName("newUserName");

        UserDto requestBodyUserDto = UserMapper.INSTANCE.mapUserDto(userToUpdate);

        when(userService.update(id, userToUpdate)).thenReturn(userToUpdate);

        //when
        mockMvc.perform(put("/user/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestBodyUserDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(requestBodyUserDto.getName()));

        //then
        verify(userService, times(1)).update(id, userToUpdate);
    }

    @Test
    void deleteUserTest() throws Exception{
        //given
        doNothing().when(userService).delete(id);

        //when
        mockMvc.perform(delete("/user/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(userService, times(1)).delete(id);
    }
}
