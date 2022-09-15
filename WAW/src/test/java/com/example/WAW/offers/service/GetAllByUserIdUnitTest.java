package com.example.WAW.offers.service;

import com.example.WAW.Auth.domain.model.entity.User;
import com.example.WAW.Auth.domain.model.entity.UserEducation;
import com.example.WAW.Auth.domain.model.entity.UserExperience;
import com.example.WAW.Auth.domain.model.entity.UserProject;
import com.example.WAW.Auth.domain.persistence.UserEducationRepository;
import com.example.WAW.Auth.domain.persistence.UserRepository;
import com.example.WAW.Auth.domain.service.UserEducationService;
import com.example.WAW.Auth.service.UserEducationServiceImpl;
import com.example.WAW.Chat.domain.model.entity.ChatRoom;
import com.example.WAW.Chat.domain.model.entity.Message;
import com.example.WAW.Company.domain.model.entity.Company;
import com.example.WAW.offers.domain.model.entity.Offer;
import com.example.WAW.offers.domain.model.entity.Petition;
import com.example.WAW.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllByUserIdUnitTest {

    Validator validator = mock(Validator.class);

    @Mock
    UserEducationRepository userEducationRepository = mock(UserEducationRepository.class);
    @Mock
    UserRepository userRepository = mock(UserRepository.class);

    @InjectMocks
    UserEducationService userEducationService = new UserEducationServiceImpl(
            userEducationRepository,userRepository, validator);

    Date generateDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(dateStr);

        return date;
    }
    User createUser() throws ParseException {
        List<UserEducation> educations = new ArrayList<>();

        List<UserExperience> experiences = new ArrayList<>();

        List<UserProject> projects = new ArrayList<>();

        List<ChatRoom> chatRooms = new ArrayList<>();

        List<Message> messages = new ArrayList<>();

        List<Petition> petitions = new ArrayList<>();

        Date birthdate = generateDate("01/11/2000");;

        User user = new User(
                1L, "name", "user", "email@email.com", "Peru", 3, "asad", "dadd", birthdate, "pass",
                educations, experiences, projects, chatRooms, messages, petitions);

        return  user;
    }
    UserEducation createUserEducation(){
        UserEducation userEducation = new UserEducation(
                1L,"UPC","description",2019,2024,null,null);

        return userEducation;
    }

    @Test
    void GetUserEducationExist() throws ParseException {
        User user =createUser();
        UserEducation education =createUserEducation();
        List<UserEducation> educations = new ArrayList<>();

        education.setUser(user);
        educations.add(education);

        when(userEducationRepository.findAllByUserId(1L)).thenReturn(educations);

        List<UserEducation> result = userEducationService.getAllByUserId(1L);

        Assertions.assertEquals(educations, result);
    }

    @Test
    void GetUserEducationNotExist() throws ParseException {
        User user =createUser();
        List<UserEducation> educations = new ArrayList<>();

        when(userEducationRepository.findAllByUserId(1L)).thenReturn(educations);

        List<UserEducation> result = userEducationService.getAllByUserId(1L);

        Assertions.assertEquals(true, result.isEmpty());
    }
}
