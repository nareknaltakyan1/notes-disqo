package com.notes.disqo.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@javax.persistence.Table(name = "Note")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Note extends AbstractEntity {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "note", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FileMetadata> fileMetadata;
}
