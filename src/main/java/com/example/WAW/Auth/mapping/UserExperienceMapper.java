package com.example.WAW.Auth.mapping;

import com.example.WAW.Auth.domain.model.entity.UserExperience;
import com.example.WAW.Auth.resource.CreateResource.CreateUserExperienceResource;
import com.example.WAW.Auth.resource.resource.UserExperienceResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class UserExperienceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public UserExperienceResource toResource(UserExperience model){
        return mapper.map(model, UserExperienceResource.class);
    }

    public UserExperience toModel(CreateUserExperienceResource resource){
        return mapper.map(resource, UserExperience.class);
    }

    public List<UserExperienceResource> modelListToResource(List<UserExperience> modelList){
        return mapper.mapList(modelList, UserExperienceResource.class);
    }
}
