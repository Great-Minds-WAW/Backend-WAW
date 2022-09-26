package com.example.WAW.Company.domain.service;

import com.example.WAW.Company.domain.model.entity.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {

    List<Company> getAll();
    Company getById(Long id);
    Company create(Company request);
    Company update(Long id, Company request);
    ResponseEntity<?> delete(Long id);
}
