package com.shakirov.database.repository;

import com.shakirov.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


//@Repository  // Spring будет искать не только аннотацию Repository, но и все, что наследует
// org.springframework.data.repository.Repository, ищет эти реализации класс JpaRepositoriesAutoConfiguration
//@Repository
//@Transact
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Optional, Entity, Future
//    @Query(name = "Company.findByName")

    @Query("""
            SELECT c
            FROM Company AS c
            JOIN FETCH c.locales AS cl
            WHERE lower(c.name) = lower(:name)
            """)
    Optional<Company> findByName(@Param("name") String name);

    // Collection, Stream(batchSize, close) - это из Java
    // Streamable, Slice, Page - это из Spring
    List<Company> findAllByNameContainingIgnoreCase(String fragment); // Containing = LIKE '%fragment%'

}
