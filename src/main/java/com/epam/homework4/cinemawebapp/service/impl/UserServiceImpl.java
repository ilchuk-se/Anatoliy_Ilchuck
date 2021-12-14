package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.businesslogic.OptionalChecker;
import com.epam.homework4.cinemawebapp.businesslogic.OptionalCheckerImpl;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.repository.UserRepository;
import com.epam.homework4.cinemawebapp.service.UserService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public final class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OptionalChecker<User> userOptionalChecker = new OptionalCheckerImpl();

    @Override
    public User getById(final Long id) throws NoSuchElementException{
        return userOptionalChecker.getValueIfPresent(
                userRepository.findById(id),
                "User with id: " + id + " was not found."
        );
    }

    @Override
    public User getAuthorized(final String login,final String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User create(final User user) {
        return userRepository.save(user);
    }

    private User editUser(final User source,final User target){
        target.setName(source.getName());
        target.setPassword(source.getPassword());
        return target;
    }

    @Override
    public User update(final Long id,final User user) {
        return userRepository.save(
                editUser(user, getById(id))
        );
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
