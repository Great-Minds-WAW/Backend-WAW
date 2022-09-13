package com.example.WAW.Auth.service;

import com.example.WAW.Auth.domain.model.entity.UserEducation;
import com.example.WAW.Auth.domain.persistence.UserEducationRepository;
import com.example.WAW.Auth.domain.persistence.UserRepository;
import com.example.WAW.Auth.domain.service.UserEducationService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserEducationServiceImpl implements UserEducationService {

    private static final String ENTITY="UserEducation";
    private final UserEducationRepository repository;
    private final UserRepository userRepository;
    private final Validator validator;

    public UserEducationServiceImpl(UserEducationRepository repository, UserRepository userRepository, Validator validator) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<UserEducation> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UserEducation> getAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public UserEducation getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public UserEducation create(Long userId,UserEducation request) {
        Set<ConstraintViolation<UserEducation>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            return repository.save(request);
        }).orElseThrow(()->new ResourceNotFoundException("User", userId));
    }

    @Override
    public UserEducation update(Long id, UserEducation request) {
        Set<ConstraintViolation<UserEducation>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                userEducation -> repository.save(userEducation
                        .withDescription(request.getDescription())
                        .withEndYear(request.getEndYear())
                        .withStartYear(request.getStartYear())
                        .withUniversity(request.getUniversity())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        userEducation -> {
                            repository.delete(userEducation);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
