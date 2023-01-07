package com.shakirov.database.testBeanLifeCycle;

import javax.annotation.PostConstruct;

/**
 * @author igor@shakirov-dev.ru on 15.12.2022
 * @version 1.0
 */
//@Component
public class ServiceCycle {

//    @Autowired
    private RepositoryCycle repositoryCycle;

    public void printService() {
        System.out.println("printService ServiceCycle");
        repositoryCycle.printRepository();
    }

    @PostConstruct
    public void post() {
        System.out.println("PostConstruct ServiceCycle");
    }
}
