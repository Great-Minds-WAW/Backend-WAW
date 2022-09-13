package com.example.WAW.Auth.domain.service;


import com.example.WAW.Auth.domain.model.entity.UserEducation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserEducationService {

    List<UserEducation> getAll();
    List<UserEducation> getAllByUserId(Long userId);
    UserEducation getById(Long id);
    UserEducation create(Long userId,UserEducation request);
    UserEducation update(Long id, UserEducation request);
    ResponseEntity<?> delete(Long id);
}
