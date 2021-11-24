package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Ticket {
    @NotNull
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(0)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
    private User user;

    @NotNull
    @Min(0)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "offer_id")
    private FilmOffer offer;

    @NotNull
    @Min(0)
    private int place;

    @NotNull
    @Min(0)
    private BigDecimal price;
}
