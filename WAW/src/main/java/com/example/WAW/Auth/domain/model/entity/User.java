package com.example.WAW.Auth.domain.model.entity;

import com.example.WAW.Chat.domain.model.entity.ChatRoom;
import com.example.WAW.Chat.domain.model.entity.Message;
import com.example.WAW.offers.domain.model.entity.Petition;
import com.example.WAW.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String fullName;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String location;
    @NotNull
    private Integer profileViews;
    @NotNull
    @NotBlank
    private String biography;
    @NotNull
    @NotBlank
    private String about;
    @NotNull
    private Date birthdate;
    @NotNull
    @NotBlank
    private String password;

    @OneToMany
    private List<UserEducation> educations;

    @OneToMany
    private List<UserExperience> experiences;

    @OneToMany
    private  List<UserProject> projects;

    @OneToMany
    private  List<ChatRoom> chatRooms;

    @OneToMany
    private List<Message> messages;

    @OneToMany
    private List<Petition> petitions;

}
