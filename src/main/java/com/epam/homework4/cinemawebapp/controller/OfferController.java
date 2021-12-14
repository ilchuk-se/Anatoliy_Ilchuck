package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.OfferDto;
import com.epam.homework4.cinemawebapp.mapper.OfferMapper;
import com.epam.homework4.cinemawebapp.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public final class OfferController {

    private final OfferService filmOfferService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/offer")
    public List<OfferDto> getAll() {
        return OfferMapper.INSTANCE.mapOfferDtos(
                filmOfferService.getAll()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/offer/{id}")
    public OfferDto getById(@PathVariable final Long id) {
        return OfferMapper.INSTANCE.mapOfferDto(
                filmOfferService.getById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/offer")
    public OfferDto create(@RequestBody final OfferDto offerDto){
        return OfferMapper.INSTANCE.mapOfferDto(
                filmOfferService.create(OfferMapper.INSTANCE.mapOffer(offerDto))
        );
    }

    @DeleteMapping(value = "/offer/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        filmOfferService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
