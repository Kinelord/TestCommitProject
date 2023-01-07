package com.web.config.base;

/**
 * @author igor@shakirov-dev.ru on 28.12.2022
 * @version 1.0
 */
public class BaseClass {
    static {
        System.out.println("BaseClass static");
    }
    protected int defaultValue;
    public int protectedValue;

    public BaseClass() {
        this.defaultValue = 1;
        this.protectedValue = 2;
    }
    static class OtherClass extends BaseClass {

        static {
            System.out.println("OtherClass static");
        }
        public OtherClass() {
            super();
            this.defaultValue = 10;
            this.protectedValue = 11;
//            BaseClass baseClass = new BaseClass();
//            baseClass.protectedValue = 10;
        }
    }
}

