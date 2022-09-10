package com.example.WAW.Auth.service;

import com.example.WAW.Auth.domain.model.entity.ExternalImage;
import com.example.WAW.Auth.domain.persistence.ExternalImageRepository;
import com.example.WAW.Auth.domain.service.ExternalImageService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ExternalImageServiceImpl implements ExternalImageService {

    private static final String ENTITY="ExternalImage";
    private final ExternalImageRepository repository;
    private final Validator validator;

    public ExternalImageServiceImpl(ExternalImageRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<ExternalImage> getAll() {
        return repository.findAll();
    }

    @Override
    public ExternalImage getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ExternalImage create(ExternalImage request) {
        Set<ConstraintViolation<ExternalImage>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public ExternalImage update(Long id, ExternalImage request) {
        Set<ConstraintViolation<ExternalImage>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                externalImage -> repository.save(externalImage
                        .withAlt(request.getAlt())
                        .withHref(request.getHref())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        externalImage -> {
                            repository.delete(externalImage);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
