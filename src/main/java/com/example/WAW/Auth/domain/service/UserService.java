package com.example.WAW.Auth.domain.service;

import com.example.WAW.Auth.domain.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(Long id);
    User create(User request);
    User update(Long id, User request);
    ResponseEntity<?> delete(Long id);
}
