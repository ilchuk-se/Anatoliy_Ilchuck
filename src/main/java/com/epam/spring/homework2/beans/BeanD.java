package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;

public class BeanD extends BaseBean {
    @Value("${beanD.name}")
    private String name;
    @Value("${beanD.value}")
    private int value;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"+name+'-'+value+'}';
    }

    private void customInitMethod(){
        System.out.println("inside customInitMethod() - " + this.getClass().getSimpleName());
    }

    private void customDestroyMethod(){
        System.out.println("inside customDestroyMethod() - " + this.getClass().getSimpleName());
    }
}
