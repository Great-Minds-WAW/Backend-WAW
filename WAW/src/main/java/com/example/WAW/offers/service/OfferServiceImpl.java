package com.example.WAW.offers.service;

import com.example.WAW.offers.domain.model.entity.Offer;
import com.example.WAW.offers.domain.persistence.OfferRepository;
import com.example.WAW.offers.domain.service.OfferService;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String ENTITY = "Offer";
    private final OfferRepository offerRepository;
    private final Validator validator;

    public OfferServiceImpl(OfferRepository offerRepository, Validator validator) {
        this.offerRepository = offerRepository;
        this.validator = validator;
    }

    @Override
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getById(Long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, offerId));
    }

    @Override
    public Offer create(Offer request) {
        Set<ConstraintViolation<Offer>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return offerRepository.save(request);
    }

    @Override
    public Offer update(Long offerId, Offer request) {
        Set<ConstraintViolation<Offer>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(ENTITY, offerId);

        return offerRepository.findById(offerId).map(
                offer -> offerRepository.save(offer
                        .withTitle(request.getTitle())
                        .withDescription(request.getDescription())
                        .withImage(request.getImage())
                        .withSalaryRange(request.getSalaryRange())
                        .withStatus(request.getStatus())
                )).orElseThrow(
                        ()->new ResourceNotFoundException(ENTITY, offerId)
                );
    }

    @Override
    public ResponseEntity<?> delete(Long offerId) {
        return offerRepository.findById(offerId).map(
                offer -> {
                    offerRepository.delete(offer);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, offerId));
    }
}
