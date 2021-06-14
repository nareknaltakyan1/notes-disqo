package com.notes.disqo.dto;

import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FileMetadataDTO {

    private Long id;

    private String originalName;

    private String guid;

    private String type;

    private String path;

    private Date createdAt;
}
