package com.epam.homework4.cinemawebapp.controller;

import com.epam.homework4.cinemawebapp.exception.HallControllerException;
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
    public List<CinemaHall> getAllHalls() {
        return  hallService.listHalls();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/hall/{id}")
    public CinemaHall getHallById(@PathVariable String id) throws HallControllerException {
        CinemaHall hall = null;
        try {
            Long hallId = Long.parseLong(id);
            hall = hallService.getHallById(hallId);
        }catch (NumberFormatException ex){
            String message = "Can not cast hall id to int";
            log.info(message);
            throw new HallControllerException(message, ex.getCause());
        }
        return hall;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/hall")
    public CinemaHall createHall(@RequestBody @Valid CinemaHall hall){
        return  hallService.createHall(hall);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/hall/{id}")
    public CinemaHall updateHall(@PathVariable String id, @RequestBody @Valid CinemaHall hall) throws HallControllerException {
        CinemaHall hallUpdated = null;
        try {
            Long hallId = Long.parseLong(id);
            hallUpdated = hallService.updateHall(hallId, hall);
        }catch (NumberFormatException ex){
            String message = "Can not cast hall id to int";
            log.info(message);
            throw new HallControllerException(message, ex.getCause());
        }
        return hallUpdated;
    }

    @DeleteMapping(value = "/hall/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable String id) throws HallControllerException{
        try {
            Long hallId = Long.parseLong(id);
            hallService.deleteHall(hallId);
        }catch (NumberFormatException ex){
            String message = "Can not cast hall id to int";
            log.info(message);
            throw new HallControllerException(message, ex.getCause());
        }

        return ResponseEntity.noContent().build();
    }
}
