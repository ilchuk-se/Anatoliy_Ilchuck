package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.repository.IUserRepository;
import com.epam.homework4.cinemawebapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(int id) {
        log.info("getUser by id {}", id);
        User user = userRepository.getUserById(id);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserAuthorized(User user) {
        log.info("getUser by login {}, pass {}", user.getLogin(), user.getPassword());
        User userAuthorized = userRepository.getUserAuthorized(user);
        return userMapper.toUserDto(userAuthorized);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userMapper.toUserDtoList(userRepository.listUsers());
    }

    @Override
    public UserDto createUser(User user) {
        log.info("createUser with email {}", user.getLogin());
        userRepository.createUser(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        log.info("updateUser with email {}", userDto.getLogin());
        User user = userMapper.signUpToUser(userDto);
        user = userRepository.updateUser(id, user);
        return userMapper.toUserDto(user);
    }

    @Override
    public void deleteUser(int id) {
        log.info("deleteUser with id {}", id);
        userRepository.deleteUser(id);
    }
}
