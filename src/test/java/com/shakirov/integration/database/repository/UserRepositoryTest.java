package com.shakirov.integration.database.repository;

import com.shakirov.dto.PersonalInfo;
import com.shakirov.dto.PersonalInfoImpl;
import com.shakirov.dto.UserFilter;
import com.shakirov.model.Role;
import com.shakirov.model.User;
import com.shakirov.database.querydsl.QPredicates;
import com.shakirov.database.repository.CompanyRepository;
import com.shakirov.database.repository.UserRepository;
import com.shakirov.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.shakirov.model.QUser.user;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Test
    void test() {
        System.out.println();
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {

        List<PersonalInfoImpl> users = userRepository.findAllByCompanyAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
        System.out.println(users);
    }

    @Test
    void checkJdbcTemplateBatchSize() {

        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println(users);
    }

    @Test
    @Disabled
    void checkJdbcTemplateBatchSizeNamed() {

        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRoleNamed(users);
        System.out.println(users);
    }

    @Test
//    @Commit
    void checkAuditingEnvers() {

        User user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkAuditing() {

        User user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementationFilter() {

        UserFilter filter = new UserFilter(
                null, "%ov%", LocalDate.now()
        );

        List<User> users = userRepository.findAllByFilter(filter);
        assertThat(users).hasSize(4);
        System.out.println(users);

    }

    @Test
    void checkCustomQueryDSL() {

        UserFilter filter = new UserFilter(
                null, "ov", LocalDate.now()
        );

        List<User> users = userRepository.findAllByFilterForQueryDSL(filter);
        assertThat(users).hasSize(4);
        System.out.println(users);

    }


    @Test
    void checkQuerydslPredicate() {

        UserFilter filter = new UserFilter(
                null, "ova", LocalDate.now()
        );
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .buildAnd();

        Iterable<User> users = userRepository.findAll(predicate);
        assertThat(users).hasSize(1);
        users.forEach(System.out::println);

    }

    @Test
    void checkProjections() {

//        List<PersonalInfoImpl> users = userRepository.findAllByCompanyId(1, PersonalInfoImpl.class);
        List<PersonalInfo> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
        System.out.println(users);

    }


    @Test
    void checkEntityGraph() {

        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));
        Page<User> slice = userRepository
                .findAllBy(pageable);

        assertThat(slice).hasSize(2);

        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkPageable() {

        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));
        // Там где 1, отсчет с 0, какой фрагмент таблицы будет возвращен, при условии что таблица будет делиться
        // на фрагменты размером 2 строки. Т.е. Если мы укажем что делится будет по 3 строки,
        // и будем просить получить 0 фрагмент то вернется первые 3 идентификатора, если будем просить 1 фрагмент,
        // то вернется идентификаторы 4, 5, 6

/*        Slice<User> slice = userRepository
                .findAllBy(pageable);

        assertThat(slice).hasSize(2);

        slice.forEach(System.out::println);

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(System.out::println);
        }*/
        Page<User> slice = userRepository
                .findAllBy(pageable);

        assertThat(slice).hasSize(2);

        slice.forEach(System.out::println);

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(System.out::println);
        }
    }

    @Test
    void checkOneSort() {
        Sort sortByBirthDate = Sort.by("birthDate");
        List<User> allUsers = userRepository
                .findTop3ByBirthDateBefore(
                        LocalDate.now(),
                        sortByBirthDate.descending());
        assertThat(allUsers).hasSize(3);
        allUsers.forEach(System.out::println);

    }

    @Test
    void checkTwoSort() {

        Sort and = Sort.by("firstname").and(Sort.by("lastname"));
        List<User> allUsersSortAnd = userRepository
                .findTop3ByBirthDateBefore(
                        LocalDate.now(),
                        and.descending());
        assertThat(allUsersSortAnd).hasSize(3);
        allUsersSortAnd.forEach(System.out::println);

    }

    @Test
    void checkClassSort() {

        Sort.TypedSort<User> sort = Sort.sort(User.class);
        Sort sortByClass = sort.by(User::getFirstname)
                .and(sort.by(User::getLastname));
        List<User> allUsersSortByClass = userRepository
                .findTop3ByBirthDateBefore(
                        LocalDate.now(),
                        sortByClass);
        assertThat(allUsersSortByClass).hasSize(3);
        allUsersSortByClass.forEach(System.out::println);

    }

    @Test
    void checkFirstTop() {
        List<User> allUsers = userRepository.findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate.of(2001, 11, 23));
        assertThat(allUsers).hasSize(3);
        allUsers.forEach(System.out::println);

        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkQueries() {
        Set<User> allBy = Set.copyOf(userRepository.findAllBy("a", "ov"));
        allBy.stream().map(User::getFirstname).sorted().forEach(System.out::println);

    }

    @Test
    void checkUpdate() {
        Optional<User> byId = userRepository.findById(1L);
        User user = byId.get();
        assertEquals(user.getRole(), Role.ADMIN);


        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

//        user.getCompany();  // LAzyInitializationException

        Optional<User> byIdAfterUpdate = userRepository.findById(1L);
        User userAfterUpdate = byIdAfterUpdate.get();
        assertEquals(userAfterUpdate.getRole(), Role.USER);

    }

    @Test
    void checkInsert() {

/*        User build = User.builder()
                .birthDate(LocalDate.now())
                .company(companyRepository.findById(1).get())
                .firstname("test First Name")
                .lastname("test Last Name")
                .role(Role.USER)
                .username("email.com")
                .userChats(null)
                .build();*/
        userRepository.findAll();
//        user.setBirthDate(LocalDate.now());
//        userRepository.save(user);
    }

}