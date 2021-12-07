package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.FilmOfferDto;
import com.epam.homework4.cinemawebapp.model.FilmOffer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmOfferMapper {
    FilmOfferMapper INSTANCE = Mappers.getMapper(FilmOfferMapper.class);

    FilmOfferDto mapFilmOfferDto(FilmOffer filmOffer);

    List<FilmOfferDto> mapFilmOfferDtos(List<FilmOffer> filmOffers);

    FilmOffer mapFilmOffer(FilmOfferDto filmOfferDto);
}
