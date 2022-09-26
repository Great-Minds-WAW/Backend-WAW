package com.example.WAW.offers.domain.persistence;

import com.example.WAW.offers.domain.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}
