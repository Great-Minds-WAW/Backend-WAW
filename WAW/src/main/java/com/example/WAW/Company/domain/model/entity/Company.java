package com.example.WAW.Company.domain.model.entity;

import com.example.WAW.offers.domain.model.entity.Offer;
import com.example.WAW.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @OneToMany
    private List<Offer> offers;
}
