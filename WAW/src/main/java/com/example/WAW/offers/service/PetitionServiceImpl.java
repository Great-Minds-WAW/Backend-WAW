package com.example.WAW.offers.service;


import com.example.WAW.Auth.domain.model.entity.UserExperience;
import com.example.WAW.Auth.domain.persistence.UserEducationRepository;
import com.example.WAW.Auth.domain.persistence.UserExperienceRepository;
import com.example.WAW.Auth.domain.persistence.UserRepository;
import com.example.WAW.offers.domain.model.entity.Petition;
import com.example.WAW.offers.domain.persistence.OfferRepository;
import com.example.WAW.offers.domain.persistence.PetitionRepository;
import com.example.WAW.offers.domain.service.PetitionService;
import com.example.WAW.shared.exception.ResourceAlreadyExistsException;
import com.example.WAW.shared.exception.ResourceNotEnoughException;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import com.example.WAW.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class PetitionServiceImpl implements PetitionService {

    private static final String ENTITY="Petition";
    private final PetitionRepository repository;
    private final UserRepository userRepository;
    private final UserEducationRepository userEducationRepository;
    private final UserExperienceRepository userExperienceRepository;
    private final OfferRepository offerRepository;
    private final Validator validator;

    public PetitionServiceImpl(PetitionRepository repository, UserRepository userRepository, UserEducationRepository userEducationRepository, UserExperienceRepository userExperienceRepository, OfferRepository offerRepository, Validator validator) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userEducationRepository = userEducationRepository;
        this.userExperienceRepository = userExperienceRepository;
        this.offerRepository = offerRepository;
        this.validator = validator;
    }

    @Override
    public List<Petition> getAll() {
        return repository.findAll();
    }

    @Override
    public Petition getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Petition getByOfferIdAndUserId(Long userId, Long offerId) {
        return repository.findByUserIdAndOfferId(userId, offerId);
    }

    public Long getUserExperience(Long userId){
        List<UserExperience> experiences = userExperienceRepository.findAllByUserId(userId);

        if (experiences.isEmpty())
            throw new ResourceNotFoundException("This user don't have none experience register");
        Long totalExperience = 0L;
        for (UserExperience experience: experiences){
            totalExperience += experience.getEndDate().getTime() - experience.getStartDate().getTime();
        }
        TimeUnit time = TimeUnit.DAYS;
        Long diff = time.convert(totalExperience, TimeUnit.MILLISECONDS);
        diff = diff / 365;
        return diff;
    }

    @Override
    public Petition create(Long userId, Long offerId,Petition request) {
        Set<ConstraintViolation<Petition>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            return offerRepository.findById(offerId).map(offer -> {
                request.setOffer(offer);

                if(getByOfferIdAndUserId(userId, offerId) != null)
                    throw  new ResourceAlreadyExistsException("This Petition Already Exist");

                if(userEducationRepository.findAllByUserId(userId).isEmpty())
                    throw new ResourceNotFoundException("This user don't have none education register");

                if(offer.getNecessaryExperience()!=0)
                    if (offer.getNecessaryExperience()>getUserExperience(userId))
                        throw new ResourceNotEnoughException("this user don't have enough experience");

                return repository.save(request);
            }).orElseThrow(()->new ResourceNotFoundException("Offer", offerId));
        }).orElseThrow(()-> new ResourceNotFoundException("User", userId));
    }

    @Override
    public Petition update(Long id, Petition request) {
        Set<ConstraintViolation<Petition>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                petition -> repository.save(petition
                        .withStatus(request.getStatus())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        petition -> {
                            repository.delete(petition);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
