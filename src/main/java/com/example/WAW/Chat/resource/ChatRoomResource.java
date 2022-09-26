package com.example.WAW.Chat.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChatRoomResource {

    private Long id;
    private Date createdAt;
    private Date updateAt;
    private Long userId;
}
