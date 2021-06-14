package com.notes.disqo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file_metadata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FileMetadata extends AbstractEntity {

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "guid", nullable = false, length = 2147483647)
    private String guid;

    @Column(name = "type")
    private String type;

    @Column(name = "path", nullable = false, length = 2147483647)
    private String path;

    @ManyToOne
    @JoinColumn(name = "NOTE_ID")
    private Note note;
}
