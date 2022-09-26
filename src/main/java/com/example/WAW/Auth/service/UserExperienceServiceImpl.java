package com.example.WAW.Auth.service;

import com.example.WAW.Auth.domain.model.entity.UserExperience;
import com.example.WAW.Auth.domain.persistence.UserExperienceRepository;
import com.example.WAW.Auth.domain.persistence.UserRepository;
import com.example.WAW.Auth.domain.service.UserExperienceService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserExperienceServiceImpl implements UserExperienceService {

    private static final String ENTITY="UserExperience";
    private final UserExperienceRepository repository;
    private final UserRepository userRepository;
    private final Validator validator;

    public UserExperienceServiceImpl(UserExperienceRepository repository, UserRepository userRepository, Validator validator) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.validator = validator;
    }


    @Override
    public List<UserExperience> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UserExperience> getAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public UserExperience getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public UserExperience create(Long userId,UserExperience request) {
        Set<ConstraintViolation<UserExperience>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            return repository.save(request);
        }).orElseThrow(()->new ResourceNotFoundException("User", userId));
    }

    @Override
    public UserExperience update(Long id, UserExperience request) {
        Set<ConstraintViolation<UserExperience>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                userExperience -> repository.save(userExperience
                        .withDescription(request.getDescription())
                        .withEndDate(request.getEndDate())
                        .withLocation(request.getLocation())
                        .withStartDate(request.getStartDate())
                        .withTimeDiff(request.getTimeDiff())
                        .withTitle(request.getTitle())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        userExperience -> {
                            repository.delete(userExperience);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
