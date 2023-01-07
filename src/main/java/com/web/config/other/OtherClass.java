package com.web.config.other;

import com.web.config.base.BaseClass;

/**
 * @author igor@shakirov-dev.ru on 28.12.2022
 * @version 1.0
 */
public class OtherClass extends BaseClass {

    static {
        System.out.println("OtherClass static");
    }
    public OtherClass() {
        super();
        this.defaultValue = 10;
        this.protectedValue = 11;
        BaseClass baseClass = new BaseClass();
        baseClass.protectedValue = 10;
    }
}