package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.model.User;

import java.util.List;

public interface UserService {
    User getById(final Long id);

    User getAuthorized(final String login,final String password);

    List<User> getAll();

    User create(final User user);

    User update(final Long id,final User user);

    void delete(final Long id);
}
