package com.epam.homework4.cinemawebapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class HallDto {

    private Long id;

    @NotNull
    @Min(1)
    private int size;

    private String schemeImageDir;

    @NotNull
    @Size(min=1)
    private String name;
}
