package com.example.WAW.offers.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateOfferResource {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String image;

    @NotNull
    private String description;

    @NotNull
    private String salaryRange;

    @NotNull
    private Boolean status;

    @NotNull
    private Long companyId;
}
