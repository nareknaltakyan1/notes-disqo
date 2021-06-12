package com.notes.disqo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notes.disqo.domain.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Positive
    private Long id;

    @Email
    private String email;

    @JsonIgnore
    private String password;

    private List<Note> notes;

    @JsonIgnore
    private LocalDateTime createDate;

    @JsonIgnore
    private LocalDateTime lastModifyDate;
}
