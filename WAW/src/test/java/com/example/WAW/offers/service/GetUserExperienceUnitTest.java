package com.example.WAW.offers.service;

import com.example.WAW.Auth.domain.model.entity.User;
import com.example.WAW.Auth.domain.model.entity.UserEducation;
import com.example.WAW.Auth.domain.model.entity.UserExperience;
import com.example.WAW.Auth.domain.model.entity.UserProject;
import com.example.WAW.Auth.domain.persistence.UserEducationRepository;
import com.example.WAW.Auth.domain.persistence.UserExperienceRepository;
import com.example.WAW.Auth.domain.persistence.UserRepository;
import com.example.WAW.Auth.domain.service.UserEducationService;
import com.example.WAW.Auth.domain.service.UserExperienceService;
import com.example.WAW.Auth.domain.service.UserService;
import com.example.WAW.Auth.service.UserEducationServiceImpl;
import com.example.WAW.Auth.service.UserExperienceServiceImpl;
import com.example.WAW.Auth.service.UserServiceImpl;
import com.example.WAW.Chat.domain.model.entity.ChatRoom;
import com.example.WAW.Chat.domain.model.entity.Message;
import com.example.WAW.Company.domain.model.entity.Company;
import com.example.WAW.Company.domain.persistence.CompanyRepository;
import com.example.WAW.offers.domain.model.entity.Offer;
import com.example.WAW.offers.domain.model.entity.Petition;
import com.example.WAW.offers.domain.persistence.OfferRepository;
import com.example.WAW.offers.domain.persistence.PetitionRepository;
import com.example.WAW.offers.domain.service.OfferService;
import com.example.WAW.offers.domain.service.PetitionService;
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

public class GetUserExperienceUnitTest {

    //Validador//
    Validator validator = mock(Validator.class);

    /*Repositories*/
    @Mock
    PetitionRepository petitionRepository = mock(PetitionRepository.class);

    /*Services*/
    @Mock
    UserEducationService userEducationService = mock(UserEducationService.class);
    @Mock
    UserExperienceService userExperienceService = mock(UserExperienceService.class);
    @Mock
    UserService userService = mock(UserService.class);
    @Mock
    OfferService offerService = mock(OfferService.class);
    @InjectMocks
    PetitionService petitionService = new PetitionServiceImpl(
            petitionRepository, userService, userEducationService, userExperienceService, offerService, validator);


    /*Generators*/
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
    Company createCompany(){

        List<Offer> offers = new ArrayList<>();

        Company company = new Company(1L, "company", "address", "email@email.com",
                offers);

        return company;
    }
    UserExperience createUserExperience(String start, String end) throws ParseException {

        Date startDate = generateDate(start);
        Date endDate = generateDate(end);

        UserExperience userExperience = new UserExperience(1L, "title", "location", startDate, endDate, "15",
                "description",null);

        return userExperience;
    }

    @Test
    void GetYearsExperienceOfUser() throws ParseException {

        User user = createUser();
        UserExperience experience = createUserExperience("01/01/2010", "01/01/2013");
        UserExperience experience1 = createUserExperience("01/01/2013", "01/01/2017");
        List<UserExperience> experiences = new ArrayList<>();

        experience.setUser(user);
        experience1.setUser(user);

        experiences.add(experience);
        experiences.add(experience1);

        when(userService.getById(1L)).thenReturn(user);
        when(userExperienceService.getAllByUserId(1L)).thenReturn(experiences);

        Long result = petitionService.getUserExperience(1L);

        Assertions.assertEquals(7, result);
    }

}
