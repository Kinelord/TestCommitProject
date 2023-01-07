package com.shakirov.aop;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author igor@shakirov-dev.ru on 14.12.2022
 * @version 1.0
 */
public class ClassA {

    ClassB classB;

    public void classA1() {
        this.classA2();
    }

    @Transactional
    public void classA2() {
        System.out.println();
    }
}
