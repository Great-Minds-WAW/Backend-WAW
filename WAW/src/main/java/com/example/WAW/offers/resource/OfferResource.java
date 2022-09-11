package com.example.WAW.offers.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferResource {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String salaryRange;
    private Boolean status;
    private Long companyId;
}