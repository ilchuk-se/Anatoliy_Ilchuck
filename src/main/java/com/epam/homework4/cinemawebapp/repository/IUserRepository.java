package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.User;

import java.util.List;

public interface IUserRepository {

    User getUserById(int id);

    User getUserAuthorized(User user);

    List<User> listUsers();

    User createUser(User user);

    User updateUser(int id, User user);

    void deleteUser(int id);
}
