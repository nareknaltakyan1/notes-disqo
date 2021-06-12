package com.notes.disqo.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@javax.persistence.Table(name = "Note")
@NoArgsConstructor
@AllArgsConstructor
public class Note extends AbstractEntity {
//-- Note:
//-- A note in the system. Notes are associated with Users.
//-- A user can have many notes. A note has the following fields:
//-- - Title: Non-blank, max 50 characters long
//-- - Note: max 1000 long
//-- - Create Time
//-- - Last Update Time
    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
