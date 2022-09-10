package com.example.WAW.offers.mapping;

import com.example.WAW.offers.domain.model.entity.Offer;
import com.example.WAW.offers.resource.CreateOfferResource;
import com.example.WAW.offers.resource.OfferResource;
import com.example.WAW.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class OfferMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public OfferResource toResource(Offer model){
        return mapper.map(model, OfferResource.class);
    }

    public List<OfferResource> modelListToResource(List<Offer> modelList){
        return mapper.mapList(modelList, OfferResource.class);
    }

    public Offer toModel(CreateOfferResource resource){
        return mapper.map(resource, Offer.class);
    }
}
