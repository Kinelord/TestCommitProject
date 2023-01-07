package com.shakirov.database.lookup;

import org.springframework.context.annotation.Scope;

//@Component
@Scope("prototype")
public class Passenger {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @PostConstruct
    public void init() {
        System.out.println("init Passenger");
    }
}
