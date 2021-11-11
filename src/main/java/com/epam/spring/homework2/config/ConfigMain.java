package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beanProcessors.MyBeanFactoryPostProcessor;
import com.epam.spring.homework2.beanProcessors.MyBeanPostProcessor;
import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.epam.spring.homework2.beans")
@PropertySource("classpath:application.properties")
public class ConfigMain {
    @Bean(
            initMethod = "customInitMethod",
            destroyMethod = "customDestroyMethod"
    )
    @DependsOn("beanD")
    public BeanB beanB(){
        return new BeanB();
    }

    @Bean(
            initMethod = "customInitMethod",
            destroyMethod = "customDestroyMethod"
    )
    @DependsOn("beanB")
    public BeanC beanC(){
        return new BeanC();
    }

    @Bean(
            initMethod = "customInitMethod",
            destroyMethod = "customDestroyMethod"
    )
    public BeanD beanD(){
        return new BeanD();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return  new MyBeanPostProcessor();
    }

    @Bean
    public MyBeanFactoryPostProcessor myBeanFactoryPostProcessor(){return new MyBeanFactoryPostProcessor();}
}
