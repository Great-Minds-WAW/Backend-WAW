package com.example.WAW.offers.domain.model.entity;

import com.example.WAW.Company.domain.model.entity.Company;
import com.example.WAW.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @NotNull
    private Integer necessaryExperience;

    @OneToMany
    private List<Petition> petitions;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;
}
