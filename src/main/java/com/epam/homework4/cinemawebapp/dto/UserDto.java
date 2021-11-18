package com.epam.homework4.cinemawebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    @NotNull
    @Min(0)
    private int id;

    @NotNull
    @Size(min=6)
    private String login;

    @NotNull
    private String name;

    @NotNull
    private String roleName;
}
