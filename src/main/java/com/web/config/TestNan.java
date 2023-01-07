package com.web.config;

public class TestNan {
    static int i1;
    public static void main(String[] args) {
//        int i2;
//        System.out.println(i2);
        System.out.println(i1);
        float real = 0.0f / 0.0f;
        if (real == real) {
            System.out.println("Equal");
        } else
            System.out.println("Not equal");

        Short s = 10;
        Integer i = 10;
        System.out.println(s.equals(i));
    }
}

