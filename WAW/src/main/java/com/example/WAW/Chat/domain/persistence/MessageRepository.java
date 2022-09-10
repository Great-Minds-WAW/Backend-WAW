package com.example.WAW.Chat.domain.persistence;

import com.example.WAW.Chat.domain.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
