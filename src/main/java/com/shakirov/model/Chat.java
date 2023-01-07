package com.shakirov.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode (of = "id")
@ToString (exclude = "userChats")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat implements BaseEntity<Long> {

    @Id
    @GeneratedValue(generator = "chat_id_seq")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "chat")
    private List<UserChat> userChats = new ArrayList<>();
}
