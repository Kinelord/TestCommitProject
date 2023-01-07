package com.shakirov.aop;

/**
 * @author igor@shakirov-dev.ru on 14.12.2022
 * @version 1.0
 */
public class ClassB extends ClassA{


    @Override
    public void classA2() {
        System.out.println("Open transaction");
        super.classA2();
        System.out.println("close");
    }
}
