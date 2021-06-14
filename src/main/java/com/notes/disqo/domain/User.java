package com.notes.disqo.domain;


import com.notes.disqo.enumeration.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@javax.persistence.Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User extends AbstractEntity{

    @Column(name = "EMAIL")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated
    @Column(name = "ROLE")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Note> notes;
}
