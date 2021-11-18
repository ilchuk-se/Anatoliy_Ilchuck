package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserDto(User user);

    List<UserDto> mapUserDtos(List<User> users);

    User mapUser(UserDto userDto);
}
