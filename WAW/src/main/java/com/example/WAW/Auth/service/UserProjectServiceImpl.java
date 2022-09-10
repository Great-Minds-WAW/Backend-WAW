package com.example.WAW.Auth.service;

import com.example.WAW.Auth.domain.model.entity.UserProject;
import com.example.WAW.Auth.domain.persistence.UserProjectRepository;
import com.example.WAW.Auth.domain.service.UserProjectService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserProjectServiceImpl implements UserProjectService {

    private static final String ENTITY="UserProject";
    private final UserProjectRepository repository;
    private final Validator validator;

    public UserProjectServiceImpl(UserProjectRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<UserProject> getAll() {
        return repository.findAll();
    }

    @Override
    public UserProject getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public UserProject create(UserProject request) {
        Set<ConstraintViolation<UserProject>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public UserProject update(Long id, UserProject request) {
        Set<ConstraintViolation<UserProject>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                userProject -> repository.save(userProject
                        .withDate(request.getDate())
                        .withSummary(request.getSummary())
                        .withTitle(request.getTitle())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        userProject -> {
                            repository.delete(userProject);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
