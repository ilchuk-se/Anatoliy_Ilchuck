package com.epam.homework4.cinemawebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class UserAuthDto {
    @NotNull
    private String login;
    @NotNull
    private String password;
}
