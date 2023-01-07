package com.shakirov.database.testBeanLifeCycle;

import javax.annotation.PostConstruct;

/**
 * @author igor@shakirov-dev.ru on 15.12.2022
 * @version 1.0
 */


//@Service
public class ControllerCycle {
    private final ServiceCycle serviceCycle;

    public ControllerCycle(ServiceCycle serviceCycle) {
        System.out.println("printService ServiceCycle");
        serviceCycle.printService();
        this.serviceCycle = serviceCycle;
    }

    @PostConstruct
    public void post() {
        System.out.println("PostConstruct ControllerCycle");
    }
}


