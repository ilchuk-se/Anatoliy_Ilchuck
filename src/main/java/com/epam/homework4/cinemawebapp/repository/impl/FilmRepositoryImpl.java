package com.epam.homework4.cinemawebapp.repository.impl;

import com.epam.homework4.cinemawebapp.model.Film;
import com.epam.homework4.cinemawebapp.repository.IFilmRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmRepositoryImpl implements IFilmRepository {

    private List<Film> list = new ArrayList<>();

    @Override
    public Film getFilmById(int id) {
        return list.stream()
                .filter(film -> film.getId() == id)
                .findFirst()
                .orElseThrow(() ->new RuntimeException("Film[id=" + id + "] is not found."));
    }

    @Override
    public List<Film> listFilms() {
        return new ArrayList<>(list);
    }

    @Override
    public Film createFilm(Film film) {
        boolean isFilmExists = list.stream().filter(f -> f.getOriginalName().equals(film.getOriginalName()))
                .findFirst().isPresent();
        if(isFilmExists){
            throw new RuntimeException("Film with name '" + film.getOriginalName() + "' already exists. Please, enter another name.");
        }

        list.add(film);
        return film;
    }

    @Override
    public Film updateFilm(int id, Film film) {
        boolean isDeleted = list.removeIf(f -> f.getId() == id);
        if (isDeleted) {
            list.add(film);
        } else {
            throw new RuntimeException("Film is not found!");
        }
        return film;
    }

    @Override
    public void deleteFilm(int id) {
        list.removeIf(film -> film.getId() == id);
    }
}
