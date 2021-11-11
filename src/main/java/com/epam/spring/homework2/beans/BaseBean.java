package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.IValidator;

public abstract class BaseBean implements IValidator {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"+name+'-'+value+'}';
    }

    @Override
    public void validate() {
        if(name == null){
            name = "";
        }
        if(value < 0){
            value = 0;
        }
    }
}
