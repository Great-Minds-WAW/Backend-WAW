package com.example.WAW.Chat.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class ChatRoomResource {

    private Long id;
    private Date createdAt;
    private Date updateAt;
}
