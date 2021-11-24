package com.epam.homework4.cinemawebapp.service.impl;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.repository.IUserRepository;
import com.epam.homework4.cinemawebapp.service.IUserService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        log.info("getUser by id {}", id);

        Optional<User> user = userRepository.findById(id);

        return UserMapper.INSTANCE.mapUserDto(user.get());
    }

    @Override
    public UserDto getUserAuthorized(User user) {
        log.info("getUser by login {}, pass {}", user.getLogin(), user.getPassword());

        User userAuthorized = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());

        return UserMapper.INSTANCE.mapUserDto(userAuthorized);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");

        List<User> allUsers = Lists.newArrayList(userRepository.findAll());
        return UserMapper.INSTANCE.mapUserDtos(allUsers);
    }

    @Override
    public UserDto createUser(User user) {
        log.info("createUser with email {}", user.getLogin());

        userRepository.save(user);

        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("updateUser with email {}", userDto.getLogin());

        User userToUpdate = userRepository.findById(id).get();

        userToUpdate.setLogin(userDto.getLogin());
        userToUpdate.setName(userDto.getName());

        userRepository.save(userToUpdate);

        return UserMapper.INSTANCE.mapUserDto(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser with id {}", id);

        userRepository.deleteById(id);
    }
}
