package com.epam.homework4.cinemawebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long id;

    @NotNull
    private UserDto user;

    @NotNull
    private OfferDto offer;

    @NotNull
    @Min(0)
    private int place;

    @NotNull
    @Min(0)
    private BigDecimal price;
}
