package com.example.WAW.Auth.resource.CreateResource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserEducationResource {
    private Long id;

    @NotNull
    private String university;

    @NotNull
    private String description;

    @NotNull
    private Integer startYear;

    @NotNull
    private Integer endYear;

    @NotNull
    private Long userId;
}
