package com.example.WAW.Chat.domain.model.entity;

import com.example.WAW.Auth.domain.model.entity.User;
import com.example.WAW.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_room")
public class ChatRoom extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany
    List<Message> messages;
}
