package com.epam.spring.homework2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE extends BaseBean{
    @PostConstruct
    public void postConstruct(){
        System.out.println("inside @PostConstruct - " + this.getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("inside @PreDestroy - " + this.getClass().getSimpleName());
    }
}
