package com.epam.homework4.cinemawebapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Film {
    @NotNull
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String originalName;

    @NotNull
    @Size(min=1)
    private String localizedName;

    @NotNull
    @Size(min=1)
    private String description;

    private float imdbRating;

    private Time timekeeping;

    private String posterImageDir;

    //@OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    //private List<FilmOffer> Offers;
}
