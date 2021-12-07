package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.repository.IUserRepository;
import com.epam.homework4.cinemawebapp.service.IUserService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final OptionalChecker<User> userOptionalChecker = new OptionalChecker<>();

    @Override
    public User getUserById(Long id) throws NoSuchElementException{
        return userOptionalChecker.getValueIfPresent(
                userRepository.findById(id),
                "User with id: " + id + " was not found."
        );
    }

    @Override
    public User getUserAuthorized(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public List<User> listUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = userOptionalChecker.getValueIfPresent(
                userRepository.findById(id),
                "User with id: " + id + " not found and can not be updated."
        );

        userToUpdate.setName(user.getName());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
