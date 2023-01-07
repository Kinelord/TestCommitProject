package com.shakirov.spting_pp.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class VerifyPropertyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("*******************************");
/*        System.out.println("BeanFactoryPostProcessor - Настройка уже созданных BeanDefinition - на этом этапе мы можем повлиять на то, какими будут наши бины еще до их создания;");
        System.out.println("VerifyPropertyBeanFactoryPostProcessor - Этот класс нужен для обработки всех BeanDefinition. Тут можно подкрутить к ним " +
                "дополнительную логику. Установить дополнительные свойства, например init or destroy методы, " +
                "отработает он даже раньше, чем реализация Spring, т.к. реализует приоритетную очередь и имеет приоритет выше чем у спринга");*/

/* BeanFactoryPostProcessor работает с метаданными конфигурации бина */
/* IoC Spring позволяет BeanFactoryPostProcessor считывать метаданные конфигурации и, возможно, изменять их
до того, как контейнер создаст какие-либо компоненты, кроме экземпляров BeanFactoryPostProcessor. */
/* Кастомный BeanFactoryPostProcessor, полезно для пользовательских конфигурационных файлов, предназначенных
для системы администраторов, переопределяющие свойства компонента, настроенные в контексте приложения */

    }

    @Override
    public int getOrder() {
        return 0;
    }

//    @PostConstruct
    void init() {
        System.out.println();
    }
}