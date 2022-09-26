package com.example.WAW.Company.service;

import com.example.WAW.Company.domain.model.entity.Company;
import com.example.WAW.Company.domain.persistence.CompanyRepository;
import com.example.WAW.Company.domain.service.CompanyService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final String ENTITY="Company";
    private final CompanyRepository repository;
    private final Validator validator;

    public CompanyServiceImpl(CompanyRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Company> getAll() {
        return repository.findAll();
    }

    @Override
    public Company getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Company create(Company request) {
        Set<ConstraintViolation<Company>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public Company update(Long id, Company request) {
        Set<ConstraintViolation<Company>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                company -> repository.save(company
                        .withAddress(request.getAddress())
                        .withEmail(request.getEmail())
                        .withName(request.getName())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        company -> {
                            repository.delete(company);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
