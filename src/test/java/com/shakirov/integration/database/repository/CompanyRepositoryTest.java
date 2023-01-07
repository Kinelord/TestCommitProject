package com.shakirov.integration.database.repository;

import com.shakirov.model.Company;
import com.shakirov.database.repository.CompanyRepository;
import com.shakirov.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
//@Transactional  // Вместо ручного вызова транзакции из entityManager.getTransaction(), ее запуска и последующего коммита
class CompanyRepositoryTest extends IntegrationTestBase {

    //@PersistenceContext ее использование не обязательно, так как Spring вставит по умолчанию @Autowired
//    private EntityManagerFactory entityManagerFactory;

//    @PersistenceUnit ее использование не обязательно, так как Spring вставит по умолчанию @Autowired и его можно явно не указывать
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;
    private static final Integer APPLE_ID = 6;

    @Test
    void checkFindByQueries() {
        Optional<Company> google = companyRepository.findByName("Google");
        companyRepository.findAllByNameContainingIgnoreCase("a");
        google.get().getLocales().forEach((k,v) -> System.out.println(v));
        System.out.println(google.get());

    }

    @Test
    void findById() {
//        entityManager.getTransaction().begin();  заменили аннотацией

        transactionTemplate.executeWithoutResult(tx -> {
            Company company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            Assertions.assertThat(company.getLocales()).hasSize(2);
        });
//        entityManager.getTransaction().commit();  заменили аннотацией
    }

    @Test
    @Disabled
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());

    }

    @Test
    void save() {
        transactionTemplate.setIsolationLevel(2);
        transactionTemplate.executeWithoutResult(tx -> {
        Company build = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Russian description",
                        "En", "English description"
                ))
                .build();
        entityManager.persist(build);
        });

    }

    @BeforeTransaction
    void printRunTransaction() {
        System.out.println("Transaction run: **************************");
    }

    @AfterTransaction
    void printStopTransaction() {
        System.out.println("Transaction stop: **************************");
    }
}