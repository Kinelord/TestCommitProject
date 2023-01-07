package com.shakirov.spting_pp.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor, PriorityOrdered {
    private final Map<String, Class> transactionBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

//        AnnotatedType[] annotatedInterfaces = bean.getClass().getAnnotatedInterfaces();
//        annotatedInterfaces.
//
//
//
//        Arrays.stream(bean.getClass().getAnnotatedInterfaces()).map(Objects::toString)
//                .filter(name -> name.contains("Repository"))
//                .forEach(b -> System.out.println(b + " - "+beanName + Arrays.toString(bean.getClass().getInterfaces())));
        if (bean.getClass().isAnnotationPresent(Transact.class)) {
//            System.out.println("Transact____________***********");
            transactionBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
/*        System.out.println("В этом участке кода, мы из нашего бина, при необходимости создаем Proxy");
        System.out.println("Proxy должно создаваться поверх полностью готового бина, поэтому вызов идет после инициализации всех его  свойств");
        System.out.println("Если над нашим классом стоит аннотация @Transact, то мы должны создать обертку поверх класса, " +
                "в котором добавим логики в наши методы. По открытию и закрытию транзакции");*/
        if (bean.getClass().isAnnotationPresent(Transact.class)) {
//            System.out.println("Transact____________***********");
            transactionBeans.put(beanName, bean.getClass());
        }

//        Arrays.stream(bean.getClass().getAnnotatedInterfaces()).map(Objects::toString)
//                .filter(name -> name.contains("Repository"))
//                .forEach(b -> System.out.println(b + " - "+beanName + bean.getClass()));

        Class beanClass = transactionBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("Open transaction");
                        try {
                            return method.invoke(bean, args);
                        }
                        catch (Exception e) {
                            System.out.println("Rollback transaction");
                            throw e;
                        }
                        finally {
                            System.out.println("Close transaction");
                        }
                    });
        }
        return bean;
    }

//    @PostConstruct
    void init() {
        System.out.println();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
