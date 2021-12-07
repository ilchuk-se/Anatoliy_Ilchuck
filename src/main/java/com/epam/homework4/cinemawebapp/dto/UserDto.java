package com.epam.homework4.cinemawebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotNull
    @Size(min=6)
    private String login;

    @NotNull
    private String name;

    private int roleId;
    private String roleName;
}
