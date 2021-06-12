package com.notes.disqo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notes.disqo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    @Positive
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String title;

    @Size(max = 1000)
    @NotBlank
    private String content;

    private User user;

    @JsonIgnore
    private LocalDateTime createDate;

    @JsonIgnore
    private LocalDateTime lastModifyDate;
}
