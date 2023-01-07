package com.web.config;

import com.shakirov.aop.ClassA;
import com.shakirov.aop.ClassB;

/**
 * @author igor@shakirov-dev.ru on 14.12.2022
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        ClassA c = new ClassB();
        c.classA1();
    }
}
