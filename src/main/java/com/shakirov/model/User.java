package com.shakirov.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NamedEntityGraph(
        name = "User.company",
        attributeNodes = {
                @NamedAttributeNode("company"),
        })
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"company", "userChats"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)  // Для  @RevisionEntity
// NOT_AUDITED - чтобы не отслеживать связанные сущности, над коллекциями это нужно указать явно над полем

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class User extends AuditingEntity<Long>{

    @Id
    @GeneratedValue(generator = "users_id_seq")
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private LocalDate birthDate;
    private String firstname;
    private String lastname;
    
    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotAudited
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();
    
    

}
