package com.epam.homework4.cinemawebapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Size(min = 5)
    private String login;

    @Size(min = 5)
    private String password;

    private String name;

    private int roleId;

    private String roleName;
}
