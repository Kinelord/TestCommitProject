//package com.shakirov.spting_pp.bpp;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//
//import java.lang.reflect.Proxy;
//
//public class TransactionBeanPP implements BeanPostProcessor {
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        if (bean.getClass().isAnnotationPresent(Transact.class)) {
//            return Proxy.newProxyInstance(bean.getClass().getClassLoader(),
//                    bean.getClass().getInterfaces(),
//                    (proxy, method, args) -> {
//                        System.out.println("Open transaction");
//                        try {
//                            return method.invoke(bean, args);
//                        } catch (Exception e) {
//                            System.out.println("Rollback transaction");
//                            throw e;
//                        } finally {
//                            System.out.println("Close transaction");
//                        }
//                    });
//
//        }
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        return bean;
//    }
//}
