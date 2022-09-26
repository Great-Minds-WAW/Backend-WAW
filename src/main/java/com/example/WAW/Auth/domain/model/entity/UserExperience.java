package com.example.WAW.Auth.domain.model.entity;

import com.example.WAW.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_experience")
public class UserExperience extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @NotBlank
    private String timeDiff;

    @NotNull
    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
