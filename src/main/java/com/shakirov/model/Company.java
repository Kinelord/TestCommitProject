package com.shakirov.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@NamedQuery(
        name = "Company.findByName",
        query = "SELECT c FROM Company AS c WHERE lower(c.name) = lower(:name)",
        lockMode = LockModeType.READ
)
@Getter
@Setter
@Builder
@EqualsAndHashCode (of = "id")
@ToString (exclude = "locales")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(generator = "company_id_seq")
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();

}
