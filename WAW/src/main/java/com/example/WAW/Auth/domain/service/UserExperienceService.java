package com.example.WAW.Auth.domain.service;


import com.example.WAW.Auth.domain.model.entity.UserExperience;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserExperienceService {
    List<UserExperience> getAll();
    List<UserExperience> getAllByUserId(Long userId);
    UserExperience getById(Long id);
    UserExperience create(Long userId, UserExperience request);
    UserExperience update(Long id, UserExperience request);
    ResponseEntity<?> delete(Long id);
}
