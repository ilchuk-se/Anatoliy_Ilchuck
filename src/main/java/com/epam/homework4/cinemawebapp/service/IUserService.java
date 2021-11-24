package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.model.User;

import java.util.List;

public interface IUserService {
    UserDto getUserById(Long id);

    UserDto getUserAuthorized(User user);

    List<UserDto> listUsers();

    UserDto createUser(User user);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
