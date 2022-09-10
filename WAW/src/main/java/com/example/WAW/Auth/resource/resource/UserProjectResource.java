package com.example.WAW.Auth.resource.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserProjectResource {
    private Long id;
    private String title;
    private String summary;
    private Date date;
}
