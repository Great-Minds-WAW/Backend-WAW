package com.example.WAW.Auth.domain.service;


import com.example.WAW.Auth.domain.model.entity.UserEducation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserEducationService {

    List<UserEducation> getAll();
    UserEducation getById(Long id);
    UserEducation create(UserEducation request);
    UserEducation update(Long id, UserEducation request);
    ResponseEntity<?> delete(Long id);
}
