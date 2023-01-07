package com.shakirov.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode (of = "id")
@ToString (exclude = {"user", "chat"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_chat")
public class UserChat implements BaseEntity<Long> {

    @Id
    @GeneratedValue(generator = "users_chat_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public void setUser(User user) {
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat) {
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }
}
