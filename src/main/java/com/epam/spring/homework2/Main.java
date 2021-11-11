package com.epam.spring.homework2;

import com.epam.spring.homework2.config.ConfigExtended;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigExtended.class);
        String[] beanNames = context.getBeanDefinitionNames();

        System.out.println(System.lineSeparator() + "List of all beans in context:");
        for (String name:beanNames) {
            System.out.println(name);
            System.out.println("Definition: " + context.getBeanDefinition(name) + System.lineSeparator());
        }

        ConfigExtended configExtended = context.getBean(ConfigExtended.class);
        System.out.println("Configuration: " + configExtended);

        System.out.println();

        context.close();
    }
}
