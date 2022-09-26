package com.example.WAW.Auth.domain.persistence;

import com.example.WAW.Auth.domain.model.entity.UserEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEducationRepository extends JpaRepository<UserEducation, Long> {
    List<UserEducation> findAllByUserId(Long userId);
}
