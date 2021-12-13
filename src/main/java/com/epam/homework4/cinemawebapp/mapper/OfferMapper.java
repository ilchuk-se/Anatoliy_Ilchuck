package com.epam.homework4.cinemawebapp.mapper;

import com.epam.homework4.cinemawebapp.dto.OfferDto;
import com.epam.homework4.cinemawebapp.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    OfferDto mapOfferDto(Offer offer);

    List<OfferDto> mapOfferDtos(List<Offer> offers);

    Offer mapOffer(OfferDto offerDto);
}
