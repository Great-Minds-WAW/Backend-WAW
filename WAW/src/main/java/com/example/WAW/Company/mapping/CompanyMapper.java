package com.example.WAW.Company.mapping;

import com.example.WAW.Company.domain.model.entity.Company;
import com.example.WAW.Company.resource.CompanyResource;
import com.example.WAW.Company.resource.CreateCompanyResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class CompanyMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public CompanyResource toResource(Company model){
        return mapper.map(model, CompanyResource.class);
    }

    public Company toModel(CreateCompanyResource resource){
        return mapper.map(resource, Company.class);
    }

    public List<CompanyResource> modelListToResource(List<Company> modelList){
        return mapper.mapList(modelList, CompanyResource.class);
    }
}
