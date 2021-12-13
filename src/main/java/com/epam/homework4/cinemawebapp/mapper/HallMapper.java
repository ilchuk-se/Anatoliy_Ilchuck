package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.HallDto;
import com.epam.homework4.cinemawebapp.model.Hall;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HallMapper {
    HallMapper INSTANCE = Mappers.getMapper(HallMapper.class);

    HallDto mapHallDto(Hall hall);

    List<HallDto> mapHallDtos(List<Hall> halls);

    Hall mapHall(HallDto hallDto);
}
