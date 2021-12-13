package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.OfferDto;
import com.epam.homework4.cinemawebapp.mapper.OfferMapper;
import com.epam.homework4.cinemawebapp.model.Offer;
import com.epam.homework4.cinemawebapp.service.OfferService;
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
public class OfferController {

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
    public OfferDto getById(@PathVariable Long id) {
        return OfferMapper.INSTANCE.mapOfferDto(
                filmOfferService.getById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/offer")
    public OfferDto create(@RequestBody OfferDto offerDto){
        Offer offerToCreate = OfferMapper.INSTANCE.mapOffer(offerDto);
        return OfferMapper.INSTANCE.mapOfferDto(
                filmOfferService.create(offerToCreate)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/offer/{id}")
    public OfferDto update(@PathVariable Long id, @RequestBody @Valid OfferDto offerDto) {
        Offer offerToUpdate = OfferMapper.INSTANCE.mapOffer(offerDto);
        return OfferMapper.INSTANCE.mapOfferDto(
                filmOfferService.update(id, offerToUpdate)
        );
    }

    @DeleteMapping(value = "/offer/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmOfferService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
