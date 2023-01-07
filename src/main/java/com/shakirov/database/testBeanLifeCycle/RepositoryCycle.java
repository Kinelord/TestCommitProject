package com.shakirov.database.testBeanLifeCycle;

import javax.annotation.PostConstruct;

/**
 * @author igor@shakirov-dev.ru on 15.12.2022
 * @version 1.0
 */
//@Component
public class RepositoryCycle {

    public void printRepository() {
        System.out.println("printRepository RepositoryCycle");
    }

    @PostConstruct
    public void post() {
        System.out.println("PostConstruct RepositoryCycle");
    }
}
