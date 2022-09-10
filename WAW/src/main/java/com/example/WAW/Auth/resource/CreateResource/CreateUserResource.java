package com.example.WAW.Auth.resource.CreateResource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
public class CreateUserResource {
    private Long id;

    @NotNull
    private String fullName;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String location;

    @NotNull
    private Integer profileViews;

    @NotNull
    private String biography;

    @NotNull
    private String about;

    @NotNull
    private Date birthdate;

    @NotNull
    private String password;
}
