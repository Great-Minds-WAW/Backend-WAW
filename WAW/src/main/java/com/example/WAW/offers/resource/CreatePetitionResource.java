package com.example.WAW.offers.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePetitionResource {

    private Long id;

    @NotNull
    private String status;

    @NotNull
    private Long offerId;

    @NotNull
    private Long userId;
}
