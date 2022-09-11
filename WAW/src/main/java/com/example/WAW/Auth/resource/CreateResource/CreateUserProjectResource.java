package com.example.WAW.Auth.resource.CreateResource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateUserProjectResource {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String summary;

    @NotNull
    private Date date;

    @NotNull
    private Long userId;

}
