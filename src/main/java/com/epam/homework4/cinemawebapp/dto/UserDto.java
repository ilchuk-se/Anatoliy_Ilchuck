package com.epam.homework4.cinemawebapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String login;
    private String name;
    private String roleName;
}
