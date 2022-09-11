package com.example.WAW.offers.domain.service;

import com.example.WAW.offers.domain.model.entity.Petition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PetitionService {
    List<Petition> getAll();
    Petition getById(Long id);
    Petition getByOfferIdAndUserId(Long userId, Long offerId);
    Petition create(Long userId, Long offerId ,Petition request);
    Petition update(Long id, Petition request);
    ResponseEntity<?> delete(Long id);
}
