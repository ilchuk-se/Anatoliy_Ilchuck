package com.epam.homework4.cinemawebapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class User {

    @NotNull
    @Min(0)
    private int id;

    @NotNull
    @Size(min=6)
    private String login;

    @NotNull
    @Size(min=6)
    private String password;

    private String name;

    private int roleId;
    private String roleName;
}
