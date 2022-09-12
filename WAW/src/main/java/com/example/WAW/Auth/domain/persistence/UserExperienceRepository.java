package com.example.WAW.Auth.domain.persistence;

import com.example.WAW.Auth.domain.model.entity.UserExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExperienceRepository extends JpaRepository<UserExperience, Long> {
    List<UserExperience> findAllByUserId(Long userId);
}
