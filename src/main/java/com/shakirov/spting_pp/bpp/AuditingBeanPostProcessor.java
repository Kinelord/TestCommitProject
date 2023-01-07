package com.shakirov.spting_pp.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditingBeanPostProcessor  implements BeanPostProcessor {

    private final Map<String, Class> auditingBeans = new HashMap<>();



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditing.class)) {
            auditingBeans.put(beanName, bean.getClass());
        }
        return bean;
    }


//    @PostConstruct
    private void initPostConstruct() {
        System.out.println("Post Construct");
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = auditingBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("Audit method: " + method.getName());
                        long startTime = System.nanoTime();
                        try {
                            return method.invoke(bean, args);
                        }finally {
                            System.out.println("Time execution: " + (System.nanoTime() - startTime));
                        }
                    });
        }
        return bean;
    }


//    @PostConstruct
    void init() {
        System.out.println();
    }
}

