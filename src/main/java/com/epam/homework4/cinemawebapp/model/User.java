package com.epam.homework4.cinemawebapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private int roleId;
    private String roleName;
}
