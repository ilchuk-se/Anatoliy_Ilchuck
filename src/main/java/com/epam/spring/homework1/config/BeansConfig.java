package com.epam.spring.homework1.config;

import com.epam.spring.homework1.beans.BeanA;
import com.epam.spring.homework1.beans.BeanB;
import com.epam.spring.homework1.beans.BeanC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.epam.spring.homework1.beans")
@Import(OtherConfig.class)
public class BeansConfig {
    @Bean
    public BeanA beanA(){
        return new BeanA();
    }

    @Bean
    public BeanB beanB(){
        return new BeanB();
    }

    @Bean
    public BeanC beanC(){
        return new BeanC();
    }
}
