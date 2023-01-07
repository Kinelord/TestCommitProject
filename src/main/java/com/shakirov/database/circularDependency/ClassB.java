package com.shakirov.database.circularDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

//@Component
@Primary
@PropertySource("classpath:application.yaml")
public class ClassB {

    private String s;

    private ClassA classA;
    @Value("${username.test1}")
    private String str;

    @Autowired
    public ClassB(@Value("${username.test2}") String s, @Lazy ClassA classA) {
        this.s = s;
        this.classA = classA;
    }

    public ClassA getClassA() {
        return classA;
    }

//    @Autowired
//    public void setClassA(ClassA classA) {
//        this.classA = classA;
//    }

    @PostConstruct
    private void init() {
        System.out.println(str + "***********  __username.test1");
        System.out.println(s + "***********  __username.test2");
    }
}
