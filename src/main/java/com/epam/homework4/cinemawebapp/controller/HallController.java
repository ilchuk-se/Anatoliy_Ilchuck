package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.CinemaHallDto;
import com.epam.homework4.cinemawebapp.mapper.CinemaHallMapper;
import com.epam.homework4.cinemawebapp.model.CinemaHall;
import com.epam.homework4.cinemawebapp.service.IHallService;
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
public class HallController {

    private final IHallService hallService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/hall")
    public List<CinemaHallDto> getAllHalls() {
        return CinemaHallMapper.INSTANCE.mapCinemaHallDtos(
                hallService.listHalls()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/hall/{id}")
    public CinemaHallDto getHallById(@PathVariable Long id) {
        return CinemaHallMapper.INSTANCE.mapCinemaHallDto(
                hallService.getHallById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/hall")
    public CinemaHallDto createHall(@RequestBody @Valid CinemaHallDto hallDto){
        CinemaHall hallToCreate = CinemaHallMapper.INSTANCE.mapCinemaHall(hallDto);
        return CinemaHallMapper.INSTANCE.mapCinemaHallDto(
                hallService.createHall(hallToCreate)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/hall/{id}")
    public CinemaHallDto updateHall(@PathVariable Long id, @RequestBody @Valid CinemaHallDto hallDto) {
        CinemaHall hallDataToUpdate = CinemaHallMapper.INSTANCE.mapCinemaHall(hallDto);
        return CinemaHallMapper.INSTANCE.mapCinemaHallDto(
                hallService.updateHall(id, hallDataToUpdate)
        );
    }

    @DeleteMapping(value = "/hall/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id){
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}
