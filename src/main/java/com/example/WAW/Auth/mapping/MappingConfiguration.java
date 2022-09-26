package com.example.WAW.Auth.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("UserConfiguration")
public class MappingConfiguration {
    @Bean
    public ExternalImageMapper externalImageMapper(){return new ExternalImageMapper();}

    @Bean
    public UserEducationMapper userEducationMapper(){return  new UserEducationMapper();}

    @Bean
    public UserExperienceMapper userExperienceMapper(){return new UserExperienceMapper();}

    @Bean
    public UserProjectMapper userProjectMapper(){return new UserProjectMapper();}

    @Bean
    public UserMapper userMapper(){return new UserMapper();}

}
