package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.UserAuthDto;
import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user")
    public List<UserDto> getAllUser(){
        return UserMapper.INSTANCE.mapUserDtos(userService.listUsers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return UserMapper.INSTANCE.mapUserDto(userService.getUserById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sign")
    public UserDto getUserAuthorized(@RequestBody UserAuthDto userAuthDto){
        return UserMapper.INSTANCE.mapUserDto(
                userService.getUserAuthorized(
                        userAuthDto.getLogin(),
                        userAuthDto.getPassword()
                )
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto createUser(@RequestBody @Valid UserDto userDto, @RequestParam String password){
        User user = UserMapper.INSTANCE.mapUser(userDto);
        user.setPassword(password);
        return UserMapper.INSTANCE.mapUserDto(userService.createUser(user));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        User userDataToUpdate = UserMapper.INSTANCE.mapUser(userDto);
        return UserMapper.INSTANCE.mapUserDto(userService.updateUser(id, userDataToUpdate));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
