package com.example.WAW.Auth.resource.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserExperienceResource {
    private Long id;
    private String title;
    private String location;
    private Date startDate;
    private Date endDate;
    private String timeDiff;
    private String description;
}
