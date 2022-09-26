package com.example.WAW.offers.mapping;

import com.example.WAW.offers.domain.model.entity.Petition;
import com.example.WAW.offers.resource.CreatePetitionResource;
import com.example.WAW.offers.resource.PetitionResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class PetitionMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public PetitionResource toResource(Petition model){
        return mapper.map(model, PetitionResource.class);
    }

    public List<PetitionResource> modelListToResource(List<Petition> modelList){
        return mapper.mapList(modelList, PetitionResource.class);
    }

    public Petition toModel(CreatePetitionResource resource){
        return mapper.map(resource, Petition.class);
    }
}
