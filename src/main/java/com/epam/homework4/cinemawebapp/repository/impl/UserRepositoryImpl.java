package com.epam.homework4.cinemawebapp.repository.impl;

import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.repository.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements IUserRepository {

    private final List<User> list = new ArrayList<>();

    @Override
    public User getUserById(int id) {
        return list.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() ->new RuntimeException("User[id=" + id + "] is not found."));
    }

    @Override
    public User getUserAuthorized(User user) {
        return list.stream()
                .filter(u -> u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElseThrow(() ->new RuntimeException("User[login=" + user.getLogin() +
                        "pass=" + user.getPassword() + "] is not found."));
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(list);
    }

    @Override
    public User createUser(User user) {
        boolean isUserExists = list.stream().filter(u -> u.getLogin().equals(user.getLogin()))
                .findFirst().isPresent();
        if(isUserExists){
            throw new RuntimeException("User with login '" + user.getLogin() + "' already exists. Please, recover your password.");
        }

        list.add(user);
        return user;
    }

    @Override
    public User updateUser(int id, User user) {
        boolean isDeleted = list.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            list.add(user);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        list.removeIf(user -> user.getId() == id);
    }
}
