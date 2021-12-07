package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.User;

import java.util.List;

public interface IUserService {
    User getUserById(Long id);

    User getUserAuthorized(String login, String password);

    List<User> listUsers();

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
