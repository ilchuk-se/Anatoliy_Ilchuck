package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.UserAuthDto;
import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.service.UserService;
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
public final class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user")
    public List<UserDto> getAll(){
        return UserMapper.INSTANCE.mapUserDtos(
                userService.getAll()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{id}")
    public UserDto getById(@PathVariable final Long id){
        return UserMapper.INSTANCE.mapUserDto(
                userService.getById(id)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sign")
    public UserDto getAuthorized(@RequestBody final UserAuthDto userAuthDto){
        return UserMapper.INSTANCE.mapUserDto(
                userService.getAuthorized(
                        userAuthDto.getLogin(),
                        userAuthDto.getPassword()
                )
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto create(@RequestBody @Valid final UserDto userDto, @RequestParam final String password){
        User user = UserMapper.INSTANCE.mapUser(userDto);
        user.setPassword(password);
        return UserMapper.INSTANCE.mapUserDto(
                userService.create(user)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{id}")
    public UserDto update(@PathVariable final Long id, @RequestBody final UserDto userDto){
        return UserMapper.INSTANCE.mapUserDto(
                userService.update(
                        id,
                        UserMapper.INSTANCE.mapUser(userDto)
                )
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
