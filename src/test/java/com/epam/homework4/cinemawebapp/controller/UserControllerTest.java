package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.service.IUserService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    private final Long id = Long.parseLong("1");
    private final String login = "login";
    private final String password = "password";

    @Test
    void getUserByIdTest() throws Exception{
        //given
        UserDto userDto = UserDto
                .builder()
                .name("TESTNAME")
                .id(id)
                .build();

        when(userService.getUserById(id)).thenReturn(userDto);

        //when
        mockMvc.perform(get("/user/" + id))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(userDto.getName()));

        //then
        verify(userService, times(1)).getUserById(id);
    }

    @Test
    void getAllUsersTest() throws Exception{
        //given
        UserDto userDto = UserDto
                .builder()
                .name("TESTNAME")
                .build();

        when(userService.listUsers()).thenReturn(Collections.singletonList(userDto));

        //when
        mockMvc.perform(get("/user"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(userDto.getName()));;

        //then
        verify(userService, times(1)).listUsers();
    }

    @Test
    void createUserTest() throws Exception{
        //given
        User userToCreate = new User();
        userToCreate.setLogin("some@mail.com");
        userToCreate.setPassword("somePass");
        userToCreate.setId(null);
        userToCreate.setName("userName");
        userToCreate.setRoleId(1);
        userToCreate.setRoleName("user");

        UserDto createdUserDto = UserMapper.INSTANCE.mapUserDto(userToCreate);

        when(userService.createUser(userToCreate)).thenReturn(createdUserDto);

        String requestBodyJson =
                "{\n" +
                "    \"login\":\"some@mail.com\",\n" +
                "    \"name\":\"userName\",\n" +
                "    \"password\":\"somePass\",\n" +
                "    \"roleName\":\"user\",\n" +
                "    \"roleId\":\"1\"\n" +
                "}";
        //when
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(createdUserDto.getName()));

        //then
        verify(userService, times(1)).createUser(userToCreate);
    }

    @Test
    void updateUserTest() throws Exception{
        //given
        User userToUpdate = new User();
        userToUpdate.setLogin("newSome@mail.com");
        userToUpdate.setName("newUserName");

        UserDto updatedUserDto = UserMapper.INSTANCE.mapUserDto(userToUpdate);

        when(userService.updateUser(id, updatedUserDto)).thenReturn(updatedUserDto);

        String requestBodyJson =
                "{\n" +
                "    \"login\":\"newSome@mail.com\",\n" +
                "    \"name\":\"newUserName\"\n" +
                "}";
        //when
        mockMvc.perform(put("/user/" + id).contentType(MediaType.APPLICATION_JSON).content(requestBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(updatedUserDto.getName()));

        //then
        verify(userService, times(1)).updateUser(id, updatedUserDto);
    }

    @Test
    void deleteUserTest() throws Exception{
        //given
        doNothing().when(userService).deleteUser(id);

        //when
        mockMvc.perform(delete("/user/" + id))
                .andExpect(status().isNoContent());

        //then
        verify(userService, times(1)).deleteUser(id);
    }
}
