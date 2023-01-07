package com.shakirov.database.repository.its_my_test;

import com.shakirov.model.Company;
import com.shakirov.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Transact
//@Auditing
@Slf4j
//@Repository(value = "company")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class CompanyRepositoryCast{

//    @InjectBean

//    @Autowired
    private final ConnectionPool pool1;
    private final List<ConnectionPool> pools;
//    @Value("${db.pool.size}")
    @Value("#{systemProperties.user.name}")
    private final Integer poolSize;

//    @PostConstruct
    private void initPostConstruct() {
        log.info("Init method CompanyRepository");
    }


    public Optional<Company> findById(Integer id) {
        System.out.println("Find by Id method");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }


    public void delete(Integer id) {
        System.out.println("Delete Method");
    }

        /*
    public static CompanyRepository of(ConnectionPool connectionPool) {
        return new CompanyRepository(connectionPool);
    }*/

    /*    private void init() {
        System.out.println("******** Init CompanyRepository *********");
    }*/

}
