package com.shakirov.database.lookup;

import com.shakirov.spting_pp.bpp.Auditing;
import org.springframework.transaction.annotation.Transactional;

//@Component
@Auditing
@Transactional
public class Car {
//    @Lookup
    public Passenger createPassenger() {
        return null;
    }

    public String drive(String name) {
        Passenger passenger = createPassenger();
        passenger.setName(name);
        return "car with " + passenger.getName();
    }

//    @PostConstruct
//    public void init () {
//        System.out.println("******************init Car");
//    }

}