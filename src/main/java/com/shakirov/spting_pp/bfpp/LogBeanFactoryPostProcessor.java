package com.shakirov.spting_pp.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LogBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("*******************************");
/*        System.out.println("LogBeanFactoryPostProcessor - отработает он позже, чем VerifyPropertyBeanFactoryPostProcessor, " +
                "т.к. имеет приоритет хуже чем другой");*/

        // Тут можно подкручивать BeanDefinition т.к. к ним есть доступ
/*        for (String beanDefinitionNames : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionNames);
            System.out.println("__________Start_______");
            System.out.println(beanDefinition);
            if (beanDefinition.getBeanClassName().equals(CompanyRepository.class.getName())) {
                beanDefinition.setInitMethodName("init");
            }
            System.out.println(beanDefinition.getInitMethodName());
            System.out.println("__________End_______\n");
        }*/
    }

    @Override
    public int getOrder() {
        return 1;
    }

//    @PostConstruct
    void init() {
        System.out.println();
    }
}
