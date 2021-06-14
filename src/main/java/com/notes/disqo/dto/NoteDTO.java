package com.notes.disqo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class NoteDTO {

    @Positive
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String title;

    @Size(max = 1000)
    @NotBlank
    private String content;

    @JsonIgnore
    private UserDTO user;

    private Date createDate;

    private Date lastModifyDate;

    private List<String> files;
}
