package com.example.WAW.Auth.domain.service;

import com.example.WAW.Auth.domain.model.entity.UserProject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserProjectService {

    List<UserProject> getAll();
    UserProject getById(Long id);
    UserProject create(UserProject request);
    UserProject update(Long id, UserProject request);
    ResponseEntity<?> delete(Long id);
}
