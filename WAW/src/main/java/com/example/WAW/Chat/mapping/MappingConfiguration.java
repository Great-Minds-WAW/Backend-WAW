package com.example.WAW.Chat.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ChatConfiguration")
public class MappingConfiguration {

    @Bean
    public MessageMapper messageMapper(){return new MessageMapper();}
}
