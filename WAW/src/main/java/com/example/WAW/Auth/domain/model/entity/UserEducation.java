package com.example.WAW.Auth.domain.model.entity;


import com.example.WAW.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_Education")
public class UserEducation extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String university;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Integer startYear;

    @NotNull
    private Integer endYear;
}
