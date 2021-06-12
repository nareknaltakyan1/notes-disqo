package com.notes.disqo.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@javax.persistence.Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{
//# A user in the system. It contains the following fields:
//# - Email: Non-blank, valid email address, unique
//# - Password: Non-blank, at least 8 characters
//# - Create Time
//# - Last Update Time
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Note> notes;
}
