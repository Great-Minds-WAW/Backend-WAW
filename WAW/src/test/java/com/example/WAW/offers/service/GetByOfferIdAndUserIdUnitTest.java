package com.example.WAW.offers.service;

import com.example.WAW.Auth.domain.model.entity.User;
import com.example.WAW.Auth.domain.model.entity.UserEducation;
import com.example.WAW.Auth.domain.model.entity.UserExperience;
import com.example.WAW.Auth.domain.model.entity.UserProject;
import com.example.WAW.Auth.domain.service.UserEducationService;
import com.example.WAW.Auth.domain.service.UserExperienceService;
import com.example.WAW.Auth.domain.service.UserService;
import com.example.WAW.Chat.domain.model.entity.ChatRoom;
import com.example.WAW.Chat.domain.model.entity.Message;
import com.example.WAW.Company.domain.model.entity.Company;
import com.example.WAW.offers.domain.model.entity.Offer;
import com.example.WAW.offers.domain.model.entity.Petition;
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

public class GetByOfferIdAndUserIdUnitTest {

    /*Validator*/
    Validator validator = mock(Validator.class);

    /*Repositories*/
    PetitionRepository petitionRepository = mock(PetitionRepository.class);

    /*Services*/
    @Mock
    UserService userService = mock(UserService.class);
    @Mock
    UserEducationService userEducationService = mock(UserEducationService.class);
    @Mock
    UserExperienceService userExperienceService =mock(UserExperienceService.class);
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
    Offer createOffer(){

        Company company = createCompany();

        List<Petition> petitions = new ArrayList<>();

        Offer offer = new Offer(1L, "oferta", "image", "description",
                "150-300", true, 3, petitions, company);

        return offer;
    }
    Petition createPetition(){
        Petition petition = new Petition(1L,"pendiente", null, null);

        return petition;
    }


    @Test
    void PetitionByOfferIdAndUserIdExist() throws ParseException {

        User user = createUser();
        Offer offer = createOffer();
        Petition petition = createPetition();

        petition.setUser(user);
        petition.setOffer(offer);

        when(userService.getById(1L)).thenReturn(user);
        when(offerService.getById(1L)).thenReturn(offer);
        when(petitionRepository.findByUserIdAndOfferId(1L,1L)).thenReturn(petition);

        Petition result = petitionService.getByOfferIdAndUserId(1L,1L);

        Assertions.assertEquals(petition, result);

    }

    @Test
    void PetitionByOfferIdAndUserIdNotExist() throws ParseException {

        User user = createUser();
        Offer offer = createOffer();
        Petition petition = createPetition();

        petition.setUser(user);
        petition.setOffer(offer);

        when(userService.getById(1L)).thenReturn(user);
        when(offerService.getById(1L)).thenReturn(offer);
        when(petitionRepository.findByUserIdAndOfferId(1L,1L)).thenReturn(null);

        Petition result = petitionService.getByOfferIdAndUserId(1L,1L);

        Assertions.assertEquals(null, result);

    }

}
