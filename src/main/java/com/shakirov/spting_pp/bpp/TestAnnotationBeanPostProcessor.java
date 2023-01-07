package com.shakirov.spting_pp.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Random;
@Component
public class TestAnnotationBeanPostProcessor  implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field f: declaredFields) {
            TestAnnotation annotation = f.getAnnotation(TestAnnotation.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                int i = min + random.nextInt(max - min);
                f.setAccessible(true);
                ReflectionUtils.setField(f, bean, i);
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

//    @PostConstruct
    void init() {
        System.out.println();
    }
}
