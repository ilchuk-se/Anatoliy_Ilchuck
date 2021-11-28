package com.epam.homework4.cinemawebapp.test.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@TestConfiguration
public class TestWebConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                //.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.epam.homework4.cinemawebapp.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }
}
