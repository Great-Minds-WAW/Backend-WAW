package com.example.WAW.Auth.mapping;

import com.example.WAW.Auth.domain.model.entity.User;
import com.example.WAW.Auth.resource.CreateResource.CreateUserResource;
import com.example.WAW.Auth.resource.resource.UserResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User model){
        return mapper.map(model, UserResource.class);
    }

    public User toModel(CreateUserResource resource){
        return mapper.map(resource, User.class);
    }

    public List<UserResource> modelListToResource(List<User> modelList){
        return mapper.mapList(modelList, UserResource.class);
    }
}
