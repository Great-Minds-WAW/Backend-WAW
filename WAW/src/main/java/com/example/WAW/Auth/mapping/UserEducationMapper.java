package com.example.WAW.Auth.mapping;

import com.example.WAW.Auth.domain.model.entity.UserEducation;
import com.example.WAW.Auth.resource.CreateResource.CreateUserEducationResource;
import com.example.WAW.Auth.resource.resource.UserEducationResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class UserEducationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public UserEducationResource toResource(UserEducation model){
        return mapper.map(model, UserEducationResource.class);
    }

    public UserEducation toModel(CreateUserEducationResource resource){
        return mapper.map(resource, UserEducation.class);
    }

    public List<UserEducationResource> modelListToResource(List<UserEducation> modelList){
        return mapper.mapList(modelList, UserEducationResource.class);
    }
}
