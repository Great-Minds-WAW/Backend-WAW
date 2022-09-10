package com.example.WAW.Chat.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateMessageResource {
    private Long id;

    @NotNull
    private String content;
}
