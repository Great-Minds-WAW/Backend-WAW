package com.example.WAW.Auth.mapping;

import com.example.WAW.Auth.domain.model.entity.ExternalImage;
import com.example.WAW.Auth.resource.CreateResource.CreateExternalImageResource;
import com.example.WAW.Auth.resource.resource.ExternalImageResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ExternalImageMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ExternalImageResource toResource(ExternalImage model){
        return mapper.map(model, ExternalImageResource.class);
    }

    public ExternalImage toModel(CreateExternalImageResource resource){
        return mapper.map(resource, ExternalImage.class);
    }

    public List<ExternalImageResource> modelListToResource(List<ExternalImage> modelList){
        return mapper.mapList(modelList, ExternalImageResource.class);
    }
}
