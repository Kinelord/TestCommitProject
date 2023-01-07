package com.shakirov.database.scope;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;

//@Component
@Scope("session")
public class RequestScope  implements InitializingBean, DisposableBean {

//    @PostConstruct
    void init() {
        System.out.println("Request bean completed");
    }

//    @PreDestroy
    void destroyPre() {
        System.out.println("Request bean delete");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Request bean completed");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Request bean delete");
    }
}