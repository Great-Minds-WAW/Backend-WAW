package com.example.WAW.Auth.domain.persistence;

import com.example.WAW.Auth.domain.model.entity.UserEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEducationRepository extends JpaRepository<UserEducation, Long> {
}
