package com.web.config;

import com.web.config.base.BaseClass;

import java.lang.reflect.InvocationTargetException;

/**
 * @author igor@shakirov-dev.ru on 28.12.2022
 * @version 1.0
 */
public abstract class ClassA {
    static {
        System.out.println("classA static");
    }
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("ClassA");
        BaseClass b = new BaseClass();
        ClassA classA = new ClassA() {
            @Override
            public void init() {
                System.out.println("Hello");
            }
        };
        System.out.println(classA.getClass().getName());
        classA.init();
        ClassA classA1 = classA.getClass().newInstance();
        classA1.init();
    }

    public abstract void init();
}
