package com.example.WAW.Auth.domain.service;

import com.example.WAW.Auth.domain.model.entity.ExternalImage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExternalImageService {

    List<ExternalImage> getAll();
    ExternalImage getById(Long id);
    ExternalImage create(ExternalImage request);
    ExternalImage update(Long id, ExternalImage request);
    ResponseEntity<?> delete(Long id);

}
