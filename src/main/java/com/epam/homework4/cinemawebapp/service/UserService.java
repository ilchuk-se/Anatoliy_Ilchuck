package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    User getAuthorized(String login, String password);

    List<User> getAll();

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);
}
