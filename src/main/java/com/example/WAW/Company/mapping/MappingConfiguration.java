package com.example.WAW.Company.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("CompanyConfiguration")
public class MappingConfiguration {

    @Bean
    public CompanyMapper companyMapper(){return new CompanyMapper();}

}
