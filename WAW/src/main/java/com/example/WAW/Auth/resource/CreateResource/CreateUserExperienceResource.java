package com.example.WAW.Auth.resource.CreateResource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateUserExperienceResource {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String location;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private String timeDiff;

    @NotNull
    private String description;
}
