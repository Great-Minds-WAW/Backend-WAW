package com.example.WAW.Chat.service;

import com.example.WAW.Auth.domain.model.entity.User;
import com.example.WAW.Auth.domain.persistence.UserRepository;
import com.example.WAW.Chat.domain.model.entity.Message;
import com.example.WAW.Chat.domain.persistence.ChatRoomRepository;
import com.example.WAW.Chat.domain.persistence.MessageRepository;
import com.example.WAW.Chat.domain.service.MessageService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String ENTITY="Message";
    private final MessageRepository repository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final Validator validator;

    public MessageServiceImpl(MessageRepository repository, UserRepository userRepository, ChatRoomRepository chatRoomRepository, Validator validator) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.validator = validator;
    }

    @Override
    public List<Message> getAll() {
        return repository.findAll();
    }

    @Override
    public Message getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Message create(Long userId, Long chatRoomId,Message request) {
        Set<ConstraintViolation<Message>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            return chatRoomRepository.findById(chatRoomId).map(chatRoom -> {
                request.setChatRoom(chatRoom);
                return repository.save(request);
            }).orElseThrow(()->new ResourceNotFoundException("ChatRoom", chatRoomId));
        }).orElseThrow(()->new ResourceNotFoundException("User", userId));
    }

    @Override
    public Message update(Long id, Message request) {
        Set<ConstraintViolation<Message>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                message -> repository.save(message
                        .withContent(request.getContent())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        message -> {
                            repository.delete(message);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
