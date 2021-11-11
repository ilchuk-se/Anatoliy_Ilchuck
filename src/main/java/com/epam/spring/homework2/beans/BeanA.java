package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA extends BaseBean implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("inside DisposableBean.destroy() method - " + this.getClass().getSimpleName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("inside InitializationBean.afterPropertiesSet() method - " + this.getClass().getSimpleName());
    }
}
