package com.shakirov.database.repository;

import com.shakirov.dto.PersonalInfo;
import com.shakirov.model.Role;
import com.shakirov.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//@RequiredArgsConstructor
//@ConditionalOnBean(value = CompanyRepositoryCast.class)
//@Transact
//@Auditing

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer>,
        QuerydslPredicateExecutor<User> {

    //    List<PersonalInfoImpl> findAllByCompanyId(Integer companyId);
//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);
    @Query(value = """
            SELECT firstname, lastname, birth_date AS birthDate
            FROM users
            WHERE company_id = :companyId
            """,
            nativeQuery = true)
    List<PersonalInfo> findAllByCompanyId(Integer companyId);


    @Query("""
            select u
            from User u
            where u.firstname like %:firstname% and u.lastname like %:lastname%
            """)
        // Запрос HQL
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = """
            SELECT u.*
            FROM users AS u
            WHERE u.username = :username;
            """,
            nativeQuery = true)
        // Запрос SQL
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("""
            Update User u
            set u.role = :role
            where u.id in (:ids)
            """)
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate birthDate);


    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    // Это дополнительные оптимизации, которые мы хотим добавить к нашему запросу
    // Если нам не нужно использовать пагинацию, то forCounting = false нужно установить
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

//    List<User> findAllBy(Pageable pageable);  // Заменим на Slice

//    Slice<User> findAllBy(Pageable pageable);  // Заменим на Page

    /*
        @Query(value = "select u from User u",  // если добавим к запросу "join fetch u.company c", то это также решит проблему множественных запросов
                countQuery = "select count(distinct u.firstname) from User u")
        Page<User> findAllBy(Pageable pageable);
    */

    //    @EntityGraph(value = "User.company")  // Если граф у нас создан на уровне класса
    @EntityGraph(attributePaths = {"company", "company.locales"})
    // Но удобнее писать графы непосредственно над запросом
    // Из больших минусов данной стратегии, что в этом случае не будет отрабатывать Pageable
    // Pageable - по факту ограничивает наш запрос ключевыми словами Limit ... offset, т.е. пагинация,
    // получение результата по страницам, но запрос графов нарушит данный запрос и в действительности будут получены все строки,
    // не ограниченные ни чем и уже на уровне приложения будет произведено отсечение ненужного результата,
    // что негативно скажется на перфомансе приложения
    @Query(value = "select u from User u",
            countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);
    
    Optional<User> findByUsername(String username);


//    @Procedure("bar")
//    String bar();
//    @Resource
//    @Qualifier("pool2")
//    private final ConnectionPool connectionPool;
}
