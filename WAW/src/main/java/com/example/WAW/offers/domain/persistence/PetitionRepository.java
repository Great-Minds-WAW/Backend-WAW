package com.example.WAW.offers.domain.persistence;

import com.example.WAW.offers.domain.model.entity.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Long> {

}
