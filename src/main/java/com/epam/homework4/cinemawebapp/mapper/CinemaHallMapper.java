package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.CinemaHallDto;
import com.epam.homework4.cinemawebapp.model.CinemaHall;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CinemaHallMapper {
    CinemaHallMapper INSTANCE = Mappers.getMapper(CinemaHallMapper.class);

    CinemaHallDto mapCinemaHallDto(CinemaHall cinemaHall);

    List<CinemaHallDto> mapCinemaHallDtos(List<CinemaHall> cinemaHalls);

    CinemaHall mapCinemaHall(CinemaHallDto cinemaHallDto);
}
