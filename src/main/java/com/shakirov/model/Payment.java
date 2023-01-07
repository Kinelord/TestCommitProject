package com.shakirov.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode (of = "id")
@ToString (exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment implements BaseEntity<Long> {

    @Id
    @GeneratedValue(generator = "payment_id_seq")
    private Long id;

    private Integer amount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User user;

}

