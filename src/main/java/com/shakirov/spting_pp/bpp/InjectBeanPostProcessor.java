package com.shakirov.spting_pp.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
@Component
public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("InjectBeanPostProcessor - Вызов перед init методами, для проверки");
//        System.out.println("Тут можно дополнительно добавить нужные свойства, допустим из контейнера спринга");
        // .getFields() вернет массив всех полей, включая унаследованные
        // .getDeclaredFields() вернет массив всех полей текущего класса, но не унаследованные

        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectBean.class))
                .forEach(field -> {
                    Object beanToInject = applicationContext.getBean(field.getType());
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, bean, beanToInject);
                });

        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


//    @PostConstruct
    void init() {
        System.out.println();
    }
}
