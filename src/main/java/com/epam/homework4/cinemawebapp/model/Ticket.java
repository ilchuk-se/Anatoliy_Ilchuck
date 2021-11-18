package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class Ticket {
    @NotNull
    @Min(0)
    private int id;

    @NotNull
    @Min(0)
    private int userId;

    @NotNull
    @Min(0)
    private int offerId;

    @NotNull
    @Min(0)
    private int place;

    @NotNull
    @Min(0)
    private BigDecimal price;
}
