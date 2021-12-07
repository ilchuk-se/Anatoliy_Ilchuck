package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.FilmDto;
import com.epam.homework4.cinemawebapp.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmDto mapFilmDto(Film film);

    List<FilmDto> mapFilmDtos(List<Film> films);

    Film mapFilm(FilmDto filmDto);
}
