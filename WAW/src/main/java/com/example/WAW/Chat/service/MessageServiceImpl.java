package com.example.WAW.Chat.service;

import com.example.WAW.Chat.domain.model.entity.Message;
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
    private final Validator validator;

    public MessageServiceImpl(MessageRepository repository, Validator validator) {
        this.repository = repository;
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
    public Message create(Message request) {
        Set<ConstraintViolation<Message>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
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
