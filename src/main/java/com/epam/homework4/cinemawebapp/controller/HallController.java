package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.dto.HallDto;
import com.epam.homework4.cinemawebapp.mapper.HallMapper;
import com.epam.homework4.cinemawebapp.model.Hall;
import com.epam.homework4.cinemawebapp.service.HallService;
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

    private final HallService hallService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/hall")
    public List<HallDto> getAll() {
        return HallMapper.INSTANCE.mapHallDtos(
                hallService.getAll()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/hall/{id}")
    public HallDto getById(@PathVariable Long id) {
        return HallMapper.INSTANCE.mapHallDto(
                hallService.getById(id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/hall")
    public HallDto create(@RequestBody @Valid HallDto hallDto){
        Hall hallToCreate = HallMapper.INSTANCE.mapHall(hallDto);
        return HallMapper.INSTANCE.mapHallDto(
                hallService.create(hallToCreate)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/hall/{id}")
    public HallDto update(@PathVariable Long id, @RequestBody @Valid HallDto hallDto) {
        Hall hallDataToUpdate = HallMapper.INSTANCE.mapHall(hallDto);
        return HallMapper.INSTANCE.mapHallDto(
                hallService.update(id, hallDataToUpdate)
        );
    }

    @DeleteMapping(value = "/hall/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        hallService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
