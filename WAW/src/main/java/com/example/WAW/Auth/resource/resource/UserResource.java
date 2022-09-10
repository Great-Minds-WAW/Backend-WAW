package com.example.WAW.Auth.resource.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class UserResource {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String location;
    private Integer profileViews;
    private String biography;
    private String about;
    private Date birthdate;
}
