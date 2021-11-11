package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

@Configuration
@Import(ConfigMain.class)
public class ConfigExtended {
    @Bean
    public BeanA beanA(){
        return new BeanA();
    }

    @Bean
    public BeanE beanE(){
        return new BeanE();
    }

    @Bean
    @Lazy
    public BeanF beanF(){
        return new BeanF();
    }
}
