package com.example.WAW.Company.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateCompanyResource {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String email;
}
