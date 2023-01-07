package com.shakirov.config.test;

import com.shakirov.database.pool.ConnectionPool;
import com.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;


// @ImportResource("classpath:application.xml")  // Можно добавить бины, сконфигурированные в xml, в нашу джава конфигурацию
@Import(WebConfiguration.class)
//@Configuration
@ComponentScan
//@PropertySource("classpath:application.properties")
//@ComponentScan(basePackages = "com.shakirov")
public class ApplicationConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean(initMethod = "initBean", destroyMethod = "destroyBean")
    public ConnectionPool pool3() {
        return new ConnectionPool("test-name", 25);
    }

/*    @Bean
    @Profile("prod|web")
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3(ConnectionPool pool2) {
        return new UserRepository(pool3());
    }*/

}
