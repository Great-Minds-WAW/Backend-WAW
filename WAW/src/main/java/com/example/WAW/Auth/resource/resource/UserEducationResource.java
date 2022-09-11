package com.example.WAW.Auth.resource.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEducationResource {
    private Long id;
    private String university;
    private String description;
    private Integer startYear;
    private Integer endYear;
    private Long userId;
}
