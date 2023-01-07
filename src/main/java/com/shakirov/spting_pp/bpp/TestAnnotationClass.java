package com.shakirov.spting_pp.bpp;

public class TestAnnotationClass {

//    @TestAnnotation (min = 2, max = 15)
    private int testAnnotation;

    private String message;

    public TestAnnotationClass() {
    }

    public TestAnnotationClass(String message) {
        this.message = message;
    }

    public void sayMessage() {
        for (int i = 0; i < testAnnotation; i++) {
            System.out.println("Message: " + message);
        }
    }
}
