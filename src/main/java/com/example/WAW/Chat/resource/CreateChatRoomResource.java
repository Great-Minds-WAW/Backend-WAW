package com.example.WAW.Chat.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateChatRoomResource {
    private Long id;

    @NotNull
    private Long userId;
}
