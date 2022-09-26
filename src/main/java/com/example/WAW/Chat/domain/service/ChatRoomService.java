package com.example.WAW.Chat.domain.service;

import com.example.WAW.Chat.domain.model.entity.ChatRoom;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoom> getAll();
    ChatRoom getById(Long id);
    ChatRoom create(Long userId,ChatRoom resource);
    //ChatRoom update(Long id, ChatRoom resource);
    ResponseEntity<?> delete(Long id);
}
