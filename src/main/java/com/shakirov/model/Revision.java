package com.shakirov.model;

import lombok.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@RevisionEntity  // Может быть только 1 на все приложение
public class Revision {

    @Id
    @GeneratedValue(generator = "revision_id_seq")
    @RevisionNumber
    private Integer id;

    @RevisionTimestamp
    private Long timestamp;
}
