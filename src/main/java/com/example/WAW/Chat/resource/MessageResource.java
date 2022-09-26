package com.example.WAW.Chat.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageResource {
    private Long id;
    private String content;
    private Date createdAt;
    private Long userId;
    private Long chatRoomId;
}
