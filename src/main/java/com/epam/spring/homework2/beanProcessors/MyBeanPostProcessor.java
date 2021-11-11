package com.epam.spring.homework2.beanProcessors;

import com.epam.spring.homework2.beans.BaseBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(System.lineSeparator() + "  << " + bean.getClass().getSimpleName() +" is inside BeanPostProcessor.postProcessAfterInitialization()");

        boolean isInstanceOfBaseBean = bean instanceof BaseBean;
        if(!isInstanceOfBaseBean){
            System.out.println("        << Bean is not instance of BaseBean");
            System.out.println();
            return bean;
        }

        BaseBean baseBean = (BaseBean)bean;
        System.out.println("        << Bean is instance of BaseBean");
        System.out.println("        << " + baseBean + ", before validation");
        baseBean.validate();
        System.out.println("        << " + baseBean + ", after validation");
        System.out.println();

        return bean;
    }
}
