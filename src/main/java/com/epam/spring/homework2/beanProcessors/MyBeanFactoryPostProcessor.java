package com.epam.spring.homework2.beanProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;


public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("beanB");
        System.out.println("inside BeanFactoryPostProcessor.postProcessBeanFactory()");
        System.out.println("Definition of bean - 'BeanB': " + beanDefinition);
        System.out.println("Changing property 'initMethod' in bean 'BeanB' from 'customInitMethod' to 'customInitMethodChanged'");

        beanDefinition.setInitMethodName("customInitMethodChanged");

        System.out.println("New definition of bean - 'BeanB': " + beanDefinition);
    }
}
