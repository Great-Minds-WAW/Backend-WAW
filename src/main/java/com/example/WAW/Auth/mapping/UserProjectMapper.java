package com.example.WAW.Auth.mapping;

import com.example.WAW.Auth.domain.model.entity.UserProject;
import com.example.WAW.Auth.resource.CreateResource.CreateUserProjectResource;
import com.example.WAW.Auth.resource.resource.UserProjectResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class UserProjectMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public UserProjectResource toResource(UserProject model){
        return mapper.map(model, UserProjectResource.class);
    }

    public UserProject toModel(CreateUserProjectResource resource){
        return mapper.map(resource, UserProject.class);
    }

    public List<UserProjectResource> modelListToResource(List<UserProject> modelList){
        return mapper.mapList(modelList, UserProjectResource.class);
    }
}
