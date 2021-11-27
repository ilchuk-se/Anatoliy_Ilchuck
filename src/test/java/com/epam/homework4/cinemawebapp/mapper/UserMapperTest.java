package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final Long id = Long.parseLong("1");
    private final String login = "login";
    private final String password = "password";
    private final String name = "TestUser";
    private final int roleId = 1;
    private final String roleName = "user";

    @Test
    void mapUserDtoTest(){
        User userToTest = new User();
        userToTest.setName(name);
        userToTest.setId(id);
        userToTest.setLogin(login);
        userToTest.setPassword(password);
        userToTest.setRoleId(roleId);
        userToTest.setRoleName(roleName);

        UserDto expectedUserDto = new UserDto(id, login, name, roleName);

        UserDto userDto = UserMapper.INSTANCE.mapUserDto(userToTest);
        assertEquals(userDto, expectedUserDto);
    }

    @Test
    void mapUserTest(){
        UserDto userDtoToTest = new UserDto(id, login, name, roleName);

        User expectedUser = new User();
        expectedUser.setName(name);
        expectedUser.setId(id);
        expectedUser.setLogin(login);
        expectedUser.setRoleName(roleName);

        User user = UserMapper.INSTANCE.mapUser(userDtoToTest);

        assertEquals(user, expectedUser);
    }
}
