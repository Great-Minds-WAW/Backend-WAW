package com.example.WAW.offers.domain.service;

import com.example.WAW.offers.domain.model.entity.Offer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfferService {

    List<Offer> getAll();
    Offer getById(Long offerId);
    Offer create(Offer request);
    Offer update(Long offerId, Offer request);
    ResponseEntity<?> delete(Long offerId);

}
