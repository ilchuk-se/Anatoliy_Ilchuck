package com.epam.homework4.cinemawebapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@Entity
public class FilmOffer {
    @NotNull
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Time time;

    @NotNull
    @Min(0)
    private float price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "film_id")
    private Film film;

    @NotNull
    @Min(0)
    private int hallId;
}
