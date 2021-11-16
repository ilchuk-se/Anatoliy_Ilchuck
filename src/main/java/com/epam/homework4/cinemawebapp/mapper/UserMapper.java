package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .roleName(user.getRoleName())
                .build();
    }

    public List<UserDto> toUserDtoList(List<User> users){
        return users.stream().map(user -> toUserDto(user)).collect(Collectors.toList());
    }

    public User signUpToUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .roleName(userDto.getRoleName())
                .login(userDto.getLogin())
                .build();
    }
}
