package com.example.WAW.Chat.service;

import com.example.WAW.Auth.domain.persistence.UserRepository;
import com.example.WAW.Chat.domain.model.entity.ChatRoom;
import com.example.WAW.Chat.domain.persistence.ChatRoomRepository;
import com.example.WAW.Chat.domain.service.ChatRoomService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ChatRoomImpl implements ChatRoomService {

    private static final String ENTITY="ChatRoom";
    private final ChatRoomRepository repository;
    private final UserRepository userRepository;
    private final Validator validator;

    public ChatRoomImpl(ChatRoomRepository repository, UserRepository userRepository, Validator validator) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<ChatRoom> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatRoom getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ChatRoom create(Long userId ,ChatRoom request) {
        Set<ConstraintViolation<ChatRoom>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            return repository.save(request);
        }).orElseThrow(()->new ResourceNotFoundException("user", userId));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        chatRoom -> {
                            repository.delete(chatRoom);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
