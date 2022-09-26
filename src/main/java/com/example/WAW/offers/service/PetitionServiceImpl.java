package com.example.WAW.offers.service;


import com.example.WAW.Auth.domain.model.entity.User;
import com.example.WAW.Auth.domain.model.entity.UserExperience;
import com.example.WAW.Auth.domain.service.UserEducationService;
import com.example.WAW.Auth.domain.service.UserExperienceService;
import com.example.WAW.Auth.domain.service.UserService;
import com.example.WAW.offers.domain.model.entity.Offer;
import com.example.WAW.offers.domain.model.entity.Petition;
import com.example.WAW.offers.domain.persistence.PetitionRepository;
import com.example.WAW.offers.domain.service.OfferService;
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
    private final UserService userService;
    private final UserEducationService userEducationService;
    private final UserExperienceService userExperienceService;
    private final OfferService offerService;
    private final Validator validator;

    public PetitionServiceImpl(PetitionRepository repository, UserService userService, UserEducationService userEducationService, UserExperienceService userExperienceService, OfferService offerService, Validator validator) {
        this.repository = repository;
        this.userService = userService;
        this.userEducationService = userEducationService;
        this.userExperienceService = userExperienceService;
        this.offerService = offerService;
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
        if (userService.getById(userId)==null){
            throw new ResourceNotFoundException("User", userId);
        }
        if (offerService.getById(offerId)==null){
            throw new ResourceNotFoundException("Offer",offerId);
        }
        return repository.findByUserIdAndOfferId(userId, offerId);
    }

    @Override
    public Long getUserExperience(Long userId){

        if (userService.getById(userId)==null){
            throw new ResourceNotFoundException("User", userId);
        }

        List<UserExperience> experiences = userExperienceService.getAllByUserId(userId);

        if (experiences.isEmpty()) {
            throw new ResourceNotFoundException("This user don't have none experience register");
        }
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

        User user = userService.getById(userId);
        Offer offer = offerService.getById(offerId);

        request.setUser(user);
        request.setOffer(offer);

        if(getByOfferIdAndUserId(userId, offerId) != null)
            throw  new ResourceAlreadyExistsException("This Petition Already Exist");

        if(userEducationService.getAllByUserId(userId).isEmpty())
            throw new ResourceNotFoundException("This user don't have none education register");

        if(offer.getNecessaryExperience()!=0)
            if (offer.getNecessaryExperience()>getUserExperience(userId))
                throw new ResourceNotEnoughException("this user don't have enough experience");
        return repository.save(request);
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
