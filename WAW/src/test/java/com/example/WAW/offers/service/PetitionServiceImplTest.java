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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.WAW.shared.exception.ResourceAlreadyExistsException;
import com.example.WAW.shared.exception.ResourceNotEnoughException;
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

class PetitionServiceImplTest {

    Validator validator = mock(Validator.class);

    @Mock
    PetitionRepository repository = mock(PetitionRepository.class);

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
            repository, userService, userEducationService, userExperienceService, offerService, validator
    );

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

    UserEducation createUserEducation(){
        UserEducation userEducation = new UserEducation(
                1L,"UPC","description",2019,2024,null,null);

        return userEducation;
    }

    UserExperience createUserExperience(String start, String end) throws ParseException {

        Date startDate = generateDate(start);
        Date endDate = generateDate(end);

        UserExperience userExperience = new UserExperience(1L, "title", "location", startDate, endDate, "15",
                "description",null);

        return userExperience;
    }

    /*
        Happy path: The User generate request correctly

        Since the user has registered his training,
        your work experience that is greater than or equal to that requested by the offer,
        and you are applying for the job for the first and only time

        When the request is submitted

        Then it will be generated correctly
    */

    @Test
    void HappyPathCreate() throws ParseException {

        User user = createUser();
        Offer offer = createOffer();
        Petition petition = createPetition();
        UserEducation education = createUserEducation();
        UserExperience experience = createUserExperience("01/01/2015", "01/01/2020");

        petition.setOffer(offer);
        petition.setUser(user);

        List<UserEducation> educations = new ArrayList<>();
        List<UserExperience> experiences = new ArrayList<>();

        educations.add(education);
        experiences.add(experience);

        when(userService.getById(1L)).thenReturn(user);
        when(offerService.getById(1L)).thenReturn(offer);
        when(userEducationService.getAllByUserId(1L)).thenReturn(educations);
        when(userExperienceService.getAllByUserId(1L)).thenReturn(experiences);
        when(repository.save(petition)).thenReturn(petition);

        Petition result = petitionService.create(1L,1L,petition);

        Assertions.assertEquals(petition, result);
    }

    /*
        UnHappy path: The User don't have training registered

        Since the user don't have registered his training,
        your work experience that is greater than or equal to that requested by the offer,
        and you are applying for the job for the first and only time

        When the request is submitted

        Then will return Exception to Bad Request
    */

    @Test
    void UnHappyPathNoUserEducationCreate() throws ParseException {

        User user = createUser();
        Offer offer = createOffer();
        Petition petition = createPetition();
        UserExperience experience = createUserExperience("01/01/2015", "01/01/2020");

        petition.setOffer(offer);
        petition.setUser(user);

        List<UserEducation> educations = new ArrayList<>();
        List<UserExperience> experiences = new ArrayList<>();

        experiences.add(experience);

        when(userService.getById(1L)).thenReturn(user);
        when(offerService.getById(1L)).thenReturn(offer);
        when(userEducationService.getAllByUserId(1L)).thenReturn(educations);
        when(userExperienceService.getAllByUserId(1L)).thenReturn(experiences);
        when(repository.save(petition)).thenReturn(petition);

        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            petitionService.create(1L,1L,petition);
        });
    }
    /*
        UnHappy path: The User don't have experience registered

        Since the user has registered his training, but don't have registered
        your work experience, and you are applying for the job for the first and only time

        When the request is submitted

        Then will return a not found exception
    */

    @Test
    void UnHappyPathNoUserExperienceCreate() throws ParseException {

        User user = createUser();
        Offer offer = createOffer();
        Petition petition = createPetition();
        UserEducation education = createUserEducation();

        petition.setOffer(offer);
        petition.setUser(user);

        List<UserEducation> educations = new ArrayList<>();
        List<UserExperience> experiences = new ArrayList<>();

        educations.add(education);

        when(userService.getById(1L)).thenReturn(user);
        when(offerService.getById(1L)).thenReturn(offer);
        when(userEducationService.getAllByUserId(1L)).thenReturn(educations);
        when(userExperienceService.getAllByUserId(1L)).thenReturn(experiences);
        when(repository.save(petition)).thenReturn(petition);

        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            petitionService.create(1L,1L,petition);
        });
    }

    /*
        UnHappy path: The User don't have enough experience

        Since the user has recorded his training,
        your work experience that is less than to that requested by the offer,
        and you are applying for the job for the first and only time

        When the request is submitted

        Then will return a not enough exception
    */

    @Test
    void UnHappyPathUserNotEnoughExperienceCreate() throws ParseException {

        User user = createUser();
        Offer offer = createOffer();
        Petition petition = createPetition();
        UserEducation education = createUserEducation();
        UserExperience experience = createUserExperience("01/01/2015", "01/01/2016");

        petition.setOffer(offer);
        petition.setUser(user);

        List<UserEducation> educations = new ArrayList<>();
        List<UserExperience> experiences = new ArrayList<>();

        educations.add(education);
        experiences.add(experience);

        when(userService.getById(1L)).thenReturn(user);
        when(offerService.getById(1L)).thenReturn(offer);
        when(userEducationService.getAllByUserId(1L)).thenReturn(educations);
        when(userExperienceService.getAllByUserId(1L)).thenReturn(experiences);
        when(repository.save(petition)).thenReturn(petition);

        Assertions.assertThrows(ResourceNotEnoughException.class, ()->{
            petitionService.create(1L,1L,petition);
        });
    }
    /*
        UnHappy path: The User generate request for second time

        Since the user has registered his training,
        your work experience that is greater than or equal to that requested by the offer,
        and you are applying for the job for the second time

        When the request is submitted

        Then will return resource already exist exception
    */

    @Test
    void UnHappyPathUserGenerateSecondPetitionCreate() throws ParseException {

        User user = createUser();
        Offer offer = createOffer();
        Petition petition = createPetition();
        UserEducation education = createUserEducation();
        UserExperience experience = createUserExperience("01/01/2015", "01/01/2020");

        petition.setOffer(offer);
        petition.setUser(user);

        List<UserEducation> educations = new ArrayList<>();
        List<UserExperience> experiences = new ArrayList<>();

        educations.add(education);
        experiences.add(experience);

        when(userService.getById(1L)).thenReturn(user);
        when(offerService.getById(1L)).thenReturn(offer);
        when(userEducationService.getAllByUserId(1L)).thenReturn(educations);
        when(userExperienceService.getAllByUserId(1L)).thenReturn(experiences);
        when(repository.save(petition)).thenReturn(petition);
        when(repository.findByUserIdAndOfferId(1L,1L)).thenReturn(petition);

        Assertions.assertThrows(ResourceAlreadyExistsException.class, ()->{
            petitionService.create(1L,1L,petition);
        });
    }
}