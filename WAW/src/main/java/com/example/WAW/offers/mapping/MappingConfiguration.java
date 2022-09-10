package com.example.WAW.offers.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("OfferMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public OfferMapper offerMapper(){
        return new OfferMapper();
    }
}
