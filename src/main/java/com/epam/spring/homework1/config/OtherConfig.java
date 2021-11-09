package com.epam.spring.homework1.config;

import com.epam.spring.homework1.beans.BeanA;
import com.epam.spring.homework1.other.OtherBeanA;
import com.epam.spring.homework1.other.OtherBeanB;
import com.epam.spring.homework1.other.OtherBeanC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.epam.spring.homework1.other")
@Import(PetConfig.class)
public class OtherConfig {
    @Bean
    public OtherBeanB otherBeanB(){
        return new OtherBeanB();
    }

    @Bean
    public OtherBeanC otherBeanC() {
        return new OtherBeanC();
    }
}
