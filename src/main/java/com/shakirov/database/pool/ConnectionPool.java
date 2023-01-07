package com.shakirov.database.pool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pool1")
@RequiredArgsConstructor
@Slf4j
public class ConnectionPool/* implements InitializingBean, DisposableBean*/ {

    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolSize;
/*    @Value("#{'systemProperties.user.name'.length()}")
    private final Integer poolSizeSystem;*/


//    @PostConstruct
    private void initPostConstruct() {
        log.info("Init method ConnectionPool");
    }

//    @PreDestroy
    private void preDestroy() {
        log.info("Destroy method ConnectionPool");
    }

    void initBean() {
//        System.out.println("Fuck_____________");
    }


    void destroyBean() {
//        System.out.println("Fuck_____________");
    }

// Init and Destroy
/*    private void init() {
        System.out.println("Init connection pool");
    }


    @Override  // Реализация через интерфейс нежелательна. Тут нет никакого IoC
    public void afterPropertiesSet() throws Exception {
        System.out.println("AfterPropertiesSet connection pool");
    }

    @Override
    public void destroy() throws Exception {

    }

    private void destroy() {
        System.out.println("Destroy method connection pool");
    }*/
}
