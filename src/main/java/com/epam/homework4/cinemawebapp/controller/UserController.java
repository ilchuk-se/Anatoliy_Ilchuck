package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.exception.UserControllerException;
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
        return userService.listUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{id}")
    public UserDto getUserById(@PathVariable String id) throws UserControllerException{
        UserDto userDto = null;
        try {
            Long userId = Long.parseLong(id);
            userDto = userService.getUserById(userId);
        }catch (NumberFormatException ex){
            String message = "Can not cast user id to Long";
            log.info(message);
            throw new UserControllerException(message, ex.getCause());
        }
        return userDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sign")
    public UserDto getUserAuthorized(@RequestParam User user){
        return userService.getUserAuthorized(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody @Valid UserDto userDto) throws UserControllerException {
        UserDto userDtoUpdated = null;
        try {
            Long userId = Long.parseLong(id);
            userDtoUpdated = userService.updateUser(userId, userDto);
        }catch (NumberFormatException ex){
            String message = "Can not cast user id to int";
            log.info(message);
            throw new UserControllerException(message, ex.getCause());
        }
        return userDtoUpdated;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) throws UserControllerException{
        try {
            Long userId = Long.parseLong(id);
            userService.deleteUser(userId);
        }catch (NumberFormatException ex){
            String message = "Can not cast user id to int";
            log.info(message);
            throw new UserControllerException(message, ex.getCause());
        }

        return ResponseEntity.noContent().build();
    }
}
