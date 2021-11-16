package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Ticket {
    private int id;
    private int userId;
    private int offerId;
    private int place;
    private BigDecimal price;
}
