package com.example.WAW.offers.domain.model.entity;

import com.example.WAW.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="offers")
public class Offer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    private String image;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String salaryRange;

    @NotNull
    private Boolean status;
}
