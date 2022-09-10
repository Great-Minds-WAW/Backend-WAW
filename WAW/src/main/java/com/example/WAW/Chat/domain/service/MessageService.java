package com.example.WAW.Chat.domain.service;

import com.example.WAW.Chat.domain.model.entity.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {

    List<Message> getAll();
    Message getById(Long id);
    Message create(Message resource);
    Message update(Long id, Message resource);
    ResponseEntity<?> delete(Long id);
}
