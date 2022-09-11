package com.example.WAW.Auth.resource.CreateResource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CreateExternalImageResource {

    private Long id;

    @NotNull
    private String href;

    @NotNull
    private String alt;

    @NotNull
    private Long userId;
}
